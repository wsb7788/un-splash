package com.guesthouse.model.response


data class Result(
    val id: String,
    val created_at: String,
    val width: Int,
    val height: Int,
    val blur_hash: String,
    val color: String,
    val current_user_collections: List<Any>,
    val description: String,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val urls: Urls,
    val user: User
)