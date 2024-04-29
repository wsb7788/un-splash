package com.guesthouse.local

import androidx.paging.PagingSource
import com.guesthouse.entity.LikedPhoto
import com.guesthouse.entity.Photo
import com.guesthouse.local.dao.LikedPhotoDao
import com.guesthouse.local.dao.PhotoDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val likedPhotoDao: LikedPhotoDao,
    private val photoDao: PhotoDao,
) {

    fun updatePhoto(photo: Photo): Flow<Unit> = flow {
        if (photo.likedByUser) {
            insertLikedPhoto(photo.toLikedPhoto())
        } else {
            deleteLikedPhoto(photo.id)
        }
        emit(Unit)
    }

    private suspend fun insertLikedPhoto(likedPhoto: LikedPhoto) {
        CoroutineScope(Dispatchers.IO).async {
            likedPhotoDao.insert(likedPhoto)
            photoDao.like(likedPhoto.id)
        }.await()
    }

    suspend fun deleteLikedPhoto(id: String) {
        CoroutineScope(Dispatchers.IO).async {
            likedPhotoDao.delete(id)
            photoDao.unLike(id)
        }.await()
    }


    suspend fun deleteAllPhotos() {
        CoroutineScope(Dispatchers.IO).async {
            photoDao.deleteAll()
        }.await()
    }

    suspend fun insertPhoto(vararg photo: Photo) {
        CoroutineScope(Dispatchers.IO).async {
            photoDao.insert(*photo)
        }.await()
    }

    fun getPhotos(): PagingSource<Int, Photo> {
        return photoDao.getPhotos()
    }

    fun getLikedPhotos(): PagingSource<Int, LikedPhoto> {
        return likedPhotoDao.getPhotos()
    }

}