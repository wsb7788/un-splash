package com.guesthouse.home.feed

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.guesthouse.designsystem.component.UnSplashSearchBar

@Composable
internal fun FeedScreen(

) {
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
                    //viewModel에 search 이벤트 전달
                }
            )
        }
    ) {
        TextField(modifier = Modifier.padding(it),value = "asd", onValueChange = {})
    }
}