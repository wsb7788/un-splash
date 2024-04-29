package com.guesthouse.home.favorites

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.guesthouse.designsystem.widget.unSplashToast
import com.guesthouse.home.feed.FeedEvent

@Composable
internal fun FavoritesRoute(
    viewModel: FavoriteViewModel = hiltViewModel()
){
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val context= LocalContext.current

    LaunchedEffect(key1 = Unit){
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED){
            viewModel.favoriteEvent.collect{ event ->
                when(event) {
                    is FavoriteEvent.Error -> unSplashToast(context, event.message)
                }

            }
        }
    }
    
    FavoritesScreen(
        uiState = uiState,
        onPhotoClicked = viewModel::onLikedPhotoClicked
    )
}