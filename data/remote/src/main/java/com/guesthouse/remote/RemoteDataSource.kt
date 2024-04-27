package com.guesthouse.remote

import com.guesthouse.model.response.SearchPhotosResponse
import com.guesthouse.remote.NetworkConstants.PAGE
import com.guesthouse.remote.NetworkConstants.PER_PAGE
import com.guesthouse.remote.NetworkConstants.PHOTOS
import com.guesthouse.remote.NetworkConstants.QUERY
import com.guesthouse.remote.NetworkConstants.SEARCH
import io.ktor.client.request.parameter
import io.ktor.http.HttpMethod
import io.ktor.http.parametersOf
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.put
import javax.inject.Inject

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
                parameter(QUERY, query)
                parameter(PAGE, page.toString())
                parameter(PER_PAGE, perPage.toString())
            }
        ).collect { emit(it) }
    }
}