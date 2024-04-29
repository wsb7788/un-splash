package com.guesthouse.local

import androidx.paging.PagingSource
import com.guesthouse.entity.LikedPhoto
import com.guesthouse.entity.Photo
import com.guesthouse.local.dao.LikedPhotoDao
import com.guesthouse.local.dao.PhotoDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val likedPhotoDao: LikedPhotoDao,
    private val photoDao: PhotoDao,
) {

    fun updatePhoto(photo: Photo) = flow {
        if(photo.likedByUser){
            insertLikedPhoto(photo.toLikedPhoto())
        }else{
            deleteLikedPhoto(photo.id)
        }
        emit(Unit)
    }

    private fun insertLikedPhoto(likedPhoto: LikedPhoto){
        likedPhotoDao.insert(likedPhoto)
        photoDao.like(likedPhoto.id)
    }

    fun deleteLikedPhoto(id: String){
        likedPhotoDao.delete(id)
        photoDao.unLike(id)
    }

    fun deleteAllPhotos(){
        photoDao.deleteAll()
    }

    fun insertPhoto(vararg photo: Photo){
        photoDao.insert(*photo)
    }

    fun getPhotos(): PagingSource<Int, Photo> {
        return photoDao.getPhotos()
    }

}