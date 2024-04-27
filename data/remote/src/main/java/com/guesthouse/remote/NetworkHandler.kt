package com.guesthouse.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.parameter
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.buildJsonObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkHandler @Inject constructor(

) {

    lateinit var accessToken: String
        private set
    var isTokenRefreshing = false

    fun setAccessToken(accessToken: String) {
        this.accessToken = accessToken
    }

    val client: HttpClient
        get() = HttpClient(CIO) {

            defaultRequest {
                headers {
                    append("Accept-Version", "v1")
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                })
            }

            install(Logging) {
                level = LogLevel.ALL
                logger = object : Logger {
                    override fun log(message: String) {
                        println(message)
                    }
                }
            }

            install(HttpTimeout) {
                requestTimeoutMillis = REQUEST_TIMEOUT
                connectTimeoutMillis = CONNECT_TIMEOUT
            }

            install(HttpRequestRetry) {
                retryOnServerErrors(maxRetries = RETRY_COUNT)
                exponentialDelay()
            }

        }

    @OptIn(InternalAPI::class)
    inline fun <reified T> request(
        method: HttpMethod,
        isAccessTokenNeeded: Boolean = true,
        crossinline url: URLBuilder.() -> Unit,
        crossinline parameter: HttpRequestBuilder.() -> Unit,
        noinline content: (JsonObjectBuilder.() -> Unit)? = null,
    ): Flow<T> = flow {
        client.use { client ->
            val request: suspend () -> HttpResponse? = {
                client.request {
                    this.method = method
                    /*if (isAccessTokenNeeded) {
                        headers {
                            append(
                                name = AUTHORIZATION,
                                value = "$BEARER $accessToken"
                            )
                        }
                    }*/
                    url {
                        protocol = URLProtocol.HTTPS
                        host = "api.unsplash.com"
                        url()
                        parameter("client_id", "2CyWpP04Dj-0x0UYj4JvQEa8xRzYqLfInpGOBIduXbM")
                        parameter()
                    }
                    content?.let {
                        contentType(ContentType.Application.Json)
                        body = Json.encodeToString(buildJsonObject {
                            content()
                        })
                    }
                }.body()
            }

            request()?.let { response ->
                if (response.status.value in SUCCESS_RANGE) {
                    emit(response.body())
                } else {
                    //todo 오류 처리
                    emit(response.body())
                }
            }
        }
    }

    companion object {
        private const val REQUEST_TIMEOUT = 600000L
        private const val RETRY_COUNT = 3
        private const val CONNECT_TIMEOUT = 10000L
        const val BASE_URL = "api.unsplash.com"

        val SUCCESS_RANGE = 200..299
    }
}