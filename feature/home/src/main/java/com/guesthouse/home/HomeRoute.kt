package com.guesthouse.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.guesthouse.navigation.HomeGraphRoute

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController) { route ->
                navController.navigate(route)
            }
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
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onNavigationItemClicked: (String) -> Unit,
) {
    NavigationBar(
        modifier = modifier.height(100.dp),
    ) {
        HomeGraphRoute.entries.forEach {
            NavigationBarItem(
                selected =  false ,
                onClick = { onNavigationItemClicked(it.name) },
                icon = { },
                label = { Text(text = it.name) }
                )
        }
    }

}
