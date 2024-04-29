package com.guesthouse.repository

import androidx.paging.PagingData
import com.guesthouse.entity.LikedPhoto
import com.guesthouse.entity.Photo
import kotlinx.coroutines.flow.Flow

interface UnSplashRepository {
    fun getSearchPhotos(query: String): Flow<PagingData<Photo>>
    fun updatePhotoLiked(photo: Photo): Flow<Unit>
    fun deleteUnLikePhoto(id: String): Flow<Unit>
}