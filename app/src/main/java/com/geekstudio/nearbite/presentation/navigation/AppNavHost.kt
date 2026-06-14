package com.geekstudio.nearbite.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.geekstudio.nearbite.presentation.home.ui.HomeRoute

@Composable
fun AppNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Home.route
    ) {

        composable(
            AppDestination.Home.route
        ) {

            HomeRoute(
                onRestaurantClick = {

                })

        }

    }

}