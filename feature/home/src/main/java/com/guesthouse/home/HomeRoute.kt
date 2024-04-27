package com.guesthouse.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.guesthouse.navigation.HomeGraphRoute

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentRoute = currentRoute,
                onNavigationItemClicked = { route ->
                    navController.navigate(route)
                },
            )
        },
    ) {
        Box(
            modifier = modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            HomeNavHost(
                navController = navController,
            )
        }
    }

}

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    modifier: Modifier = Modifier,
    onNavigationItemClicked: (String) -> Unit,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        HomeGraphRoute.entries.forEach {
            NavigationBarItem(
                selected =  it.name == currentRoute ,
                onClick = {
                    onNavigationItemClicked(it.name)
                          },
                icon = {
                       when(it.name){
                           HomeGraphRoute.FEED.name -> {
                               Icon(
                                   imageVector = Icons.Default.Add,
                                   contentDescription = stringResource(
                                       R.string.feed_bottom_navigation
                                    ),
                               )
                           }
                           HomeGraphRoute.FAVORITES.name -> {
                               Icon(
                                   imageVector = Icons.Default.Favorite,
                                   contentDescription = stringResource(
                                       R.string.favorites_bottom_navigation
                                   ),
                               )
                           }
                       }
                },
                label = {
                    Text(text = it.name)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.LightGray,
                    unselectedTextColor = Color.LightGray,
                ),
                )
        }
    }

}
