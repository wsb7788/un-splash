package com.guesthouse.unsplash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.guesthouse.designsystem.theme.UnSplashTheme
import com.guesthouse.unsplash.navigation.UnSplashRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnSplashTheme {
                UnSplashRoute()
            }
        }
    }
}