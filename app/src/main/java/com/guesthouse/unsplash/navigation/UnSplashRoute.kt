package com.guesthouse.unsplash.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
internal fun UnSplashRoute(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = Modifier,
    ) {
        UnSplashNavHost(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
            navController = navController,
        )
    }
}

