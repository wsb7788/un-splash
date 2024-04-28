package com.guesthouse.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.guesthouse.designsystem.R

@Composable
fun PhotoItem(
    modifier: Modifier = Modifier,
    photoUrl: String,
    isLiked: Boolean,
    onPhotoClicked: () -> Unit = {}
) {

    Box(
        modifier = modifier
    ) {
        AsyncImage(
            model = photoUrl,
            contentDescription = null
        )
        IconButton(
            onClick = { onPhotoClicked() },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.photo_like_button),
                tint = if (isLiked) Color.Red else Color.LightGray
            )
        }
    }

}