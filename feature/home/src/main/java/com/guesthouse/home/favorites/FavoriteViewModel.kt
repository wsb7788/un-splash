package com.guesthouse.home.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.guesthouse.entity.LikedPhoto
import com.guesthouse.usecase.LoadLikedPhotoUseCase
import com.guesthouse.usecase.UnLikePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val deleteUnLikePhoto: UnLikePhotoUseCase,
    private val loadLikedPhotoUseCase: LoadLikedPhotoUseCase,
): ViewModel() {

    private val _uiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    init {
        loadLikedPhotoUseCase.invoke()
            .cachedIn(viewModelScope)
            .onEach {data ->
                _uiState.update {
                    FavoriteUiState(
                        data
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    fun onLikedPhotoClicked(likedPhoto: LikedPhoto) {
        deleteUnLikePhoto(
            id = likedPhoto.id
        )
            .launchIn(viewModelScope)
    }

}