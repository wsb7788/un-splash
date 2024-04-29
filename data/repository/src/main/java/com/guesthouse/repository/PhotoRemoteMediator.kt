package com.guesthouse.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.guesthouse.entity.Photo
import com.guesthouse.local.LocalDataSource
import com.guesthouse.remote.RemoteDataSource
import kotlinx.coroutines.flow.first

@OptIn(ExperimentalPagingApi::class)
class PhotoRemoteMediator(
    private val query: String,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): RemoteMediator<Int, Photo>() {

    companion object {
        const val INITIAL_PAGE = 1
        const val PER_PAGE = 30
    }

    private var currentPage = INITIAL_PAGE

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Photo>): MediatorResult {
        return when(loadType){
            LoadType.REFRESH -> refresh()
            LoadType.PREPEND -> MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> loadAfter()
        }
    }

    private suspend fun refresh(): MediatorResult {

        try{

            currentPage = INITIAL_PAGE

            val response = remoteDataSource.getPhotos(
                query = query,
                page = currentPage,
                perPage = PER_PAGE
            ).first()

            if((response.total ?: 0) != 0){
                localDataSource.deleteAllPhotos()
                localDataSource.insertPhoto(*response.toPhotos().toTypedArray())
            }

            return MediatorResult.Success(endOfPaginationReached = currentPage == response.totalPages)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

    }

    private suspend fun loadAfter(): MediatorResult {

        try{

            val response = remoteDataSource.getPhotos(
                query = query,
                page = ++currentPage,
                perPage = PER_PAGE
            ).first()

            localDataSource.insertPhoto(*response.toPhotos().toTypedArray())

            return MediatorResult.Success(endOfPaginationReached = currentPage == response.totalPages)

        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }

    }

}