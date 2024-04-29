package com.guesthouse.home.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guesthouse.entity.LikedPhoto
import com.guesthouse.usecase.UnLikePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val postUnLikePhoto: UnLikePhotoUseCase,
): ViewModel() {

    private val _uiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    fun onLikedPhotoClicked(likedPhoto: LikedPhoto) {
        postUnLikePhoto(
            id = likedPhoto.id
        ).onEach {
            println("asdf")
        }
            .launchIn(viewModelScope)
    }

}