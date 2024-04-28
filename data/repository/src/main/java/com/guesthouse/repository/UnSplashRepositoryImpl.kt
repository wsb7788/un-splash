package com.guesthouse.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.guesthouse.entity.Photo
import com.guesthouse.local.LocalDataSource
import com.guesthouse.remote.PhotosPagingSource
import com.guesthouse.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UnSplashRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): UnSplashRepository {
    override fun getSearchPhotos(query: String): Flow<PagingData<Photo>> =
        Pager(
            config = PagingConfig(
                pageSize = 30,
            ),
            pagingSourceFactory = {PhotosPagingSource(remoteDataSource, query)}
        ).flow

    override fun postLikePhoto(photo: Photo) =
        // api 요청, 두 개의 db 에 저장
        localDataSource.updatePhoto(photo)

}