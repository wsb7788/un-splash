package com.guesthouse.home.favorites

sealed class FavoriteEvent {
    data class Error(val message: String) : FavoriteEvent()
}