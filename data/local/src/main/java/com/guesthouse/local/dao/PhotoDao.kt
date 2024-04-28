package com.guesthouse.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.guesthouse.entity.Photo

@Dao
interface PhotoDao{

    @Query("SELECT * FROM Photo")
    fun getPhotos(): PagingSource<Int, Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photos : Photo)

    @Query("DELETE FROM Photo")
    fun deleteAll()

}