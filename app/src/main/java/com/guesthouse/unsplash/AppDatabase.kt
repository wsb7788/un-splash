package com.guesthouse.unsplash

import androidx.room.Database
import androidx.room.RoomDatabase
import com.guesthouse.entity.LikedPhoto
import com.guesthouse.entity.Photo
import com.guesthouse.local.dao.LikedPhotoDao
import com.guesthouse.local.dao.PhotoDao

@Database(entities = [LikedPhoto::class, Photo::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getPhotoDao(): PhotoDao
    abstract fun getLikedPhotoDao(): LikedPhotoDao
}