package com.guesthouse.home.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.guesthouse.designsystem.component.PhotoItem
import com.guesthouse.designsystem.component.UnSplashSearchBar
import com.guesthouse.entity.Photo
import kotlinx.coroutines.flow.flow

@Composable
internal fun FeedScreen(
    uiState: State<FeedUiState>,
    onTextChanged: (String) -> Unit,
    onPhotoClicked: (Photo) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    val photos = remember(uiState.value.photos) {
        flow {
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
                onValueChange = onTextChanged,
                keyboardController = keyboardController,
            )
        }
    ) { paddingValues ->

        LazyGridPhotoList(
            paddingValues = paddingValues,
            photos = photos,
            onPhotoClicked = onPhotoClicked
        )

    }
}

@Composable
private fun LazyGridPhotoList(
    paddingValues: PaddingValues,
    photos: LazyPagingItems<Photo>,
    onPhotoClicked: (Photo) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding() + 5.dp),
        columns = StaggeredGridCells.Fixed(3),
        verticalItemSpacing = 3.dp,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(
            items = photos.itemSnapshotList.items,
        ) { photo ->
            PhotoItem(
                photoUrl = photo.url,
                isLiked = photo.likedByUser,
                onPhotoClicked = { onPhotoClicked(photo) }
            )
        }
    }
}