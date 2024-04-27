package com.guesthouse.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val id: String?,
    val slug: String?,
    @SerialName("created_at")
    val createdAt: String?,
    val width: Int?,
    val height: Int?,
    @SerialName("blur_hash")
    val blurHash: String?,
    val color: String?,
    @SerialName("current_user_collections")
    val currentUserCollections: List<String>,
    val description: String?,
    @SerialName("liked_by_user")
    val likedByUser: Boolean?,
    val likes: Int?,
    val links: Links?,
    val urls: Urls?,
    val user: User?
)