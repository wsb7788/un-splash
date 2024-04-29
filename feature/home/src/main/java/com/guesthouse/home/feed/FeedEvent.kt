package com.guesthouse.home.feed

sealed class FeedEvent {
    data class Error(val message: String) : FeedEvent()
}