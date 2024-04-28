package com.guesthouse.designsystem.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.guesthouse.designsystem.R

@Composable
fun PhotoItem(
    photoUrl: String,
    isLiked: Boolean,
    onPhotoClicked: ()-> Unit = {}
) {

    AsyncImage(
        modifier = Modifier
            .fillMaxWidth(),
        model = photoUrl,
        contentDescription = null
    )
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clickable { onPhotoClicked() },
        contentAlignment = Alignment.TopEnd
    ) {
        if (isLiked) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(R.string.photo_like_button),
                tint = Color.Red
            )
        } else {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = stringResource(R.string.photo_like_button),
            )
        }

    }


}