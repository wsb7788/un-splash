package com.guesthouse.remote

import com.guesthouse.model.response.LikePhotoResponse
import com.guesthouse.model.response.SearchPhotosResponse
import com.guesthouse.remote.NetworkConstants.LIKE
import com.guesthouse.remote.NetworkConstants.PAGE
import com.guesthouse.remote.NetworkConstants.PER_PAGE
import com.guesthouse.remote.NetworkConstants.PHOTOS
import com.guesthouse.remote.NetworkConstants.QUERY
import com.guesthouse.remote.NetworkConstants.SEARCH
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val networkHandler: NetworkHandler,
) {
    fun getPhotos(
        query: String,
        page: Int,
        perPage: Int,
    ): Flow<SearchPhotosResponse> = flow {
        networkHandler.request<SearchPhotosResponse>(
            method = HttpMethod.Get,
            url = {
                path(SEARCH, PHOTOS)
            },
            parameter = {
                parameter("client_id", "2CyWpP04Dj-0x0UYj4JvQEa8xRzYqLfInpGOBIduXbM")
                parameter(QUERY, query)
                parameter(PAGE, page.toString())
                parameter(PER_PAGE, perPage.toString())
            }
        ).collect { emit(it) }
    }

    fun postLikePhoto(id: String): Flow<Unit> = flow {
        networkHandler.request<LikePhotoResponse>(
            method = HttpMethod.Post,
            url = {
                path(PHOTOS, id, LIKE)
            }
        ).collect { emit(Unit) }
    }

    fun deleteLikePhoto(id: String): Flow<Unit>  = flow {
        networkHandler.request<LikePhotoResponse>(
            method = HttpMethod.Delete,
            url = {
                path(PHOTOS, id, LIKE)
            }
        ).collect { emit(Unit) }
    }

}