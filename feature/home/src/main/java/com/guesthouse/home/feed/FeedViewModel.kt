package com.guesthouse.home.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.guesthouse.entity.Photo
import com.guesthouse.usecase.LikePhotoUseCase
import com.guesthouse.usecase.SearchPhotoUseCase
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
internal class FeedViewModel @Inject constructor(
    private val getPhotos: SearchPhotoUseCase,
    private val postLikePhoto: LikePhotoUseCase,
) : ViewModel() {

    private val _feedUiState: MutableStateFlow<FeedUiState> = MutableStateFlow(FeedUiState())
    val feedUiState: StateFlow<FeedUiState> = _feedUiState.asStateFlow()

    private val _feedEvent: MutableSharedFlow<FeedEvent> = MutableSharedFlow(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val feedEvent: SharedFlow<FeedEvent> = _feedEvent.asSharedFlow()

    fun onSearchTextChanged(value: String) {
        getPhotos(value)
            .catch {
                it.message?.let { message ->
                    _feedEvent.tryEmit(FeedEvent.Error(message))
                }
            }
            .onEach { data ->
                _feedUiState.update {
                    it.copy(
                        photos = data
                    )
                }
            }
            .cachedIn(viewModelScope)
            .launchIn(viewModelScope)

    }

    fun onPhotoClicked(photo: Photo) {
        postLikePhoto(
            photo = photo.copy(
                likedByUser = !photo.likedByUser
            )
        )
            .catch {
                it.message?.let { message ->
                    _feedEvent.tryEmit(FeedEvent.Error(message))
                }
            }
            .launchIn(viewModelScope)
    }


}
