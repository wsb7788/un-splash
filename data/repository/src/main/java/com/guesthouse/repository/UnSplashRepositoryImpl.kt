package com.guesthouse.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.guesthouse.entity.Photo
import com.guesthouse.remote.PhotosPagingSource
import com.guesthouse.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnSplashRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
): UnSplashRepository {
    override fun getSearchPhotos(query: String): Flow<PagingData<Photo>> =
        Pager(
            config = PagingConfig(
                pageSize = 30,
            ),
            pagingSourceFactory = {PhotosPagingSource(remoteDataSource, query)}
        ).flow
}