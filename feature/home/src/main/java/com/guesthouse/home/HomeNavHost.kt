package com.guesthouse.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guesthouse.home.favorites.FavoritesRoute
import com.guesthouse.home.feed.FeedRoute
import com.guesthouse.navigation.HomeGraphRoute
import com.guesthouse.navigation.UnSplashRoute

@Composable
internal fun HomeNavHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = HomeGraphRoute.FEED.name,
    ) {
        composable(
            route = HomeGraphRoute.FEED.name
        ) {
            FeedRoute()
        }
        composable(
            route = HomeGraphRoute.FAVORITES.name
        ) {
            FavoritesRoute()
        }
    }
}