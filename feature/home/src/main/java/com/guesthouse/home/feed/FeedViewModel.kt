package com.guesthouse.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.guesthouse.entity.Photo
import com.guesthouse.usecase.LikePhotoUseCase
import com.guesthouse.usecase.SearchPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class FeedViewModel @Inject constructor(
    private val getPhotos: SearchPhotoUseCase,
    private val postLikePhoto: LikePhotoUseCase,
) : ViewModel() {

    private val _feedUiState: MutableStateFlow<FeedUiState> = MutableStateFlow(FeedUiState())
    val feedUiState: StateFlow<FeedUiState> = _feedUiState.asStateFlow()


    fun onSearchTextChanged(value: String) {
        getPhotos(value)
            .cachedIn(viewModelScope)
            .onEach { data ->
                _feedUiState.update {
                    it.copy(
                        photos = data
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onPhotoClicked(photo: Photo) {
        //1. api 요청으로 확인
        //2. db에 저장. 두 개의 테이블 안에 저장해야 함.
        postLikePhoto(
            photo = photo.copy(
                likedByUser = !photo.likedByUser
            )
        )
    }

}