package com.guesthouse.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikedPhoto(
    @PrimaryKey
    val id: String,
    val url: String,
)