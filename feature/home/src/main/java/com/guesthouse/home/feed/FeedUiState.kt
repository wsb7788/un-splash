package com.guesthouse.home.feed

import androidx.paging.PagingData
import com.guesthouse.entity.Photo

data class FeedUiState(
    val photos: PagingData<Photo> = PagingData.empty()
)
