package com.guesthouse.model.response

import com.guesthouse.entity.Photo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchPhotosResponse(
    val results: List<Result>,
    val total: Int?,
    @SerialName("total_pages")
    val totalPages: Int?
){
    fun toPhotos(): List<Photo> {
        return results.map {
            Photo(
                id = it.id ?: "",
                url = it.urls?.regular?:"",
                likedByUser = it.likedByUser?:false
            )
        }
    }
}