package com.guesthouse.home.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.guesthouse.designsystem.widget.unSplashToast

@Composable
internal fun FeedRoute(
    viewModel: FeedViewModel = hiltViewModel()
){
    val uiState = viewModel.feedUiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context= LocalContext.current

    LaunchedEffect(key1 = Unit){
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
            viewModel.feedEvent.collect{ event ->
                when(event) {
                    is FeedEvent.Error -> unSplashToast(context, event.message)
                }

            }
        }
    }

    FeedScreen(
        uiState = uiState,
        onTextChanged = viewModel::onSearchTextChanged,
        onPhotoClicked = viewModel::onPhotoClicked

    )
}