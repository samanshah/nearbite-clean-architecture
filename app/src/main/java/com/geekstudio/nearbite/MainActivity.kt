package com.geekstudio.nearbite

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.geekstudio.nearbite.core.ui.theme.NearBiteTheme
import com.geekstudio.nearbite.presentation.navigation.AppNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NearBiteTheme {

                AppNavHost()

            }
        }
    }
}