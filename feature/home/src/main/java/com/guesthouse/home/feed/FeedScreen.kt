package com.guesthouse.home.feed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.guesthouse.designsystem.component.UnSplashSearchBar
import com.guesthouse.entity.Photo
import com.guesthouse.home.R
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun FeedScreen(
    viewModel: FeedViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val uiState = viewModel.feedUiState.collectAsStateWithLifecycle()

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
                onValueChange = {
                    viewModel.onSearchTextChanged(it)
                },
                keyboardController = keyboardController,
            )
        }
    ) {

        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(top = it.calculateTopPadding() + 5.dp),
            columns = StaggeredGridCells.Fixed(3),
            verticalItemSpacing = 3.dp,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            items(
                items = photos.itemSnapshotList.items,
            ) { photo ->
                PhotoItem(
                    photo = photo,
                    )
            }
        }

    }
}

@Composable
fun PhotoItem(photo: Photo) {

    AsyncImage(
        modifier = Modifier
            .fillMaxWidth(),
        model = photo.url,
        contentDescription = null
    )
    Box(
        modifier = Modifier.padding(10.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        if (photo.likedByUser) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "",
                tint = Color.Red
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "",
            )
        }

    }


}