package com.guesthouse.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.guesthouse.entity.Photo
import kotlinx.coroutines.flow.first

class PhotosPagingSource(
    private val remoteDataSource: RemoteDataSource,
    private val query: String,
): PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key?: 1
        return try {
            val response = remoteDataSource.getPhotos(
                query = query,
                page = page,
                perPage = 30,
            ).first()

            val photos = response.toPhotos()

            val prevKey = if(page == 0) null else page -1
            val nextKey = if(response.totalPages == page) null else page + 1

            return LoadResult.Page(
                data = photos,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}