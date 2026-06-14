package com.geekstudio.nearbite.presentation.navigation

sealed class AppDestination(
    val route: String
) {

    data object Home : AppDestination("home")

    data object Detail : AppDestination("detail")

    data object Favorites : AppDestination("favorites")

    data object Settings : AppDestination("settings")

}