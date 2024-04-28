package com.guesthouse.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.guesthouse.entity.Photo

@Dao
interface PhotoDao{

    @Query("SELECT * FROM Photo")
    fun getPhotos(): PagingSource<Int, Photo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg photos : Photo)

    @Query("UPDATE Photo SET likedByUser = true WHERE id = :id")
    fun like(id: String)

    @Query("UPDATE Photo SET likedByUser = false WHERE id = :id")
    fun unLike(id: String)

    @Query("DELETE FROM Photo")
    fun deleteAll()

}