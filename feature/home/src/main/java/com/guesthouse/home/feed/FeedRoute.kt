package com.guesthouse.home.feed

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun FeedRoute(
    viewModel: FeedViewModel = hiltViewModel()
){
    val uiState = viewModel.feedUiState.collectAsStateWithLifecycle()

    FeedScreen(
        uiState = uiState,
        onTextChanged = viewModel::onSearchTextChanged,
        onPhotoClicked = viewModel::onPhotoClicked

    )
}