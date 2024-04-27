package com.guesthouse.model.response

import com.guesthouse.entity.Photo

data class SearchPhotosResponse(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
){
    fun toPhotos(): List<Photo> {
        return results.map {
            Photo(
                id = it.id,
                url = it.urls.regular,
                likedByUser = it.liked_by_user
            )
        }
    }
}