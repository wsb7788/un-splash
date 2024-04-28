package com.guesthouse.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guesthouse.entity.LikedPhoto
import com.guesthouse.entity.Photo

@Dao
interface LikedPhotoDao {

    @Query("SELECT * FROM LikedPhoto")
    fun getPhotos(): PagingSource<Int, LikedPhoto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(photo : LikedPhoto)

    @Query("DELETE FROM LikedPhoto WHERE id = :id")
    fun delete(id: String)

}