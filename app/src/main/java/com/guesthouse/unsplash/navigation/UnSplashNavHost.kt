package com.guesthouse.unsplash.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.guesthouse.home.HomeRoute
import com.guesthouse.navigation.UnSplashRoute

@Composable
internal fun UnSplashNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = UnSplashRoute.HOME.name
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = UnSplashRoute.HOME.name
        ){
            HomeRoute()
        }

    }
}