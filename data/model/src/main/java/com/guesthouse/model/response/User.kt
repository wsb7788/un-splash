package com.guesthouse.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("first_name")
    val firstName: String?,
    val id: String?,
    @SerialName("instagram_username")
    val instagramUsername: String?,
    @SerialName("last_name")
    val lastName: String?,
    val links: LinksX?,
    val name: String?,
    @SerialName("portfolio_url")
    val portfolioUrl: String?,
    @SerialName("profile_image")
    val profileImage: ProfileImage?,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    val username: String?
)