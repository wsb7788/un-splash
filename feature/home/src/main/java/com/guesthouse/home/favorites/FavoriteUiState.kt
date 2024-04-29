package com.guesthouse.home.favorites

import androidx.paging.PagingData
import com.guesthouse.entity.LikedPhoto

data class FavoriteUiState(
    val likedPhotos: PagingData<LikedPhoto> = PagingData.empty()
)
