package com.guesthouse.model.response

import kotlinx.serialization.Serializable

@Serializable
data class LinksX(
    val html: String?,
    val likes: String?,
    val photos: String?,
    val self: String?
)