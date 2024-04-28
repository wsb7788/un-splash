package com.guesthouse.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photo(
    @PrimaryKey
    val id: String,
    val url: String,
    val likedByUser: Boolean
){
    fun toLikedPhoto(): LikedPhoto {
        return LikedPhoto(id = id, url = url)
    }
}
