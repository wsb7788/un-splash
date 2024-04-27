package com.guesthouse.home.feed

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.guesthouse.designsystem.component.UnSplashSearchBar
import com.guesthouse.entity.Photo
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val uiState = viewModel.feedUiState.collectAsStateWithLifecycle()

    val photos = remember(uiState.value.photos){
        flow{
            emit(uiState.value.photos)
        }
    }.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .padding(
                top = 10.dp,
                start = 10.dp,
                end = 10.dp
            ),
        topBar = {
            UnSplashSearchBar(
                onValueChange = {
                    viewModel.onSearchTextChanged(it)
                },
                keyboardController = keyboardController,
            )
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it).fillMaxHeight()) {
            items(photos.itemCount) {
                photos[it]?.let { photo ->
                    PhotoItem(photo)
                }
            }
        }
    }
}

@Composable
fun PhotoItem(photo: Photo) {
    AsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        model = photo.url,
        contentDescription = null
    )
}