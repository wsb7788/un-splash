package com.guesthouse.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.guesthouse.entity.Photo
import com.guesthouse.local.LocalDataSource
import com.guesthouse.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnSplashRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): UnSplashRepository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getSearchPhotos(query: String): Flow<PagingData<Photo>> =
        Pager(
            config = PagingConfig(
                pageSize = 30,
            ),
            remoteMediator = PhotoRemoteMediator(query, remoteDataSource, localDataSource),
            pagingSourceFactory = { localDataSource.getPhotos() }
        ).flow

    override fun postLikePhoto(photo: Photo) =
        localDataSource.updatePhoto(photo)

}