package com.guesthouse.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LikePhotoResponse(
    val photo: Result,
    val user: User
)