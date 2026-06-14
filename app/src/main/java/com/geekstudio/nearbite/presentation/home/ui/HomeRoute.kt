package com.geekstudio.nearbite.presentation.home.ui

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.geekstudio.nearbite.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    onRestaurantClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    val restaurants = state.restaurants.collectAsLazyPagingItems()

    HomeScreen(
        state = state,
        restaurants = restaurants,
        onEvent = viewModel::onEvent,
        onRestaurantClick = onRestaurantClick
    )

}