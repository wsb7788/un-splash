package com.guesthouse.home.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.guesthouse.designsystem.component.PhotoItem
import com.guesthouse.designsystem.component.UnSplashTextField
import com.guesthouse.entity.LikedPhoto
import kotlinx.coroutines.flow.flow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FavoritesScreen(
    uiState: State<FavoriteUiState>,
    onPhotoClicked: (LikedPhoto) -> Unit
) {

    val likedPhotos = remember(uiState.value.likedPhotos) {
        flow {
            emit(uiState.value.likedPhotos)
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
            TopAppBar(title = {
                UnSplashTextField(value = "좋아요 남긴 목록")
            })
        }
    ) { paddingValues ->

        LazyGridLikePhotoList(
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding() + 5.dp),
            likedPhotos = likedPhotos,
            onPhotoClicked = onPhotoClicked
        )

    }
}

@Composable
private fun LazyGridLikePhotoList(
    modifier: Modifier = Modifier,
    likedPhotos: LazyPagingItems<LikedPhoto>,
    onPhotoClicked: (LikedPhoto) -> Unit
) {
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = StaggeredGridCells.Fixed(3),
        verticalItemSpacing = 3.dp,
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        items(
            items = likedPhotos.itemSnapshotList.items,
        ) { photo ->
            PhotoItem(
                photoUrl = photo.url,
                isLiked = true,
                onPhotoClicked = { onPhotoClicked(photo) }
            )
        }
    }
}