package com.guesthouse.home.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.guesthouse.entity.LikedPhoto
import com.guesthouse.usecase.LoadLikedPhotoUseCase
import com.guesthouse.usecase.UnLikePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val deleteUnLikePhoto: UnLikePhotoUseCase,
    private val loadLikedPhotoUseCase: LoadLikedPhotoUseCase,
) : ViewModel() {

    private val _uiState: MutableStateFlow<FavoriteUiState> = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> = _uiState.asStateFlow()

    private val _favoriteEvent: MutableSharedFlow<FavoriteEvent> = MutableSharedFlow(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val favoriteEvent: SharedFlow<FavoriteEvent> = _favoriteEvent.asSharedFlow()


    init {
        loadLikedPhotoUseCase.invoke()
            .catch {
                it.message?.let { message ->
                    _favoriteEvent.tryEmit(FavoriteEvent.Error(message))
                }
            }
            .onEach { data ->
                _uiState.update {
                    FavoriteUiState(
                        data
                    )
                }
            }
            .cachedIn(viewModelScope)
            .launchIn(viewModelScope)
    }

    fun onLikedPhotoClicked(likedPhoto: LikedPhoto) {
        deleteUnLikePhoto(
            id = likedPhoto.id
        )
            .catch {
                it.message?.let { message ->
                    _favoriteEvent.tryEmit(FavoriteEvent.Error(message))
                }
            }
            .launchIn(viewModelScope)
    }

}