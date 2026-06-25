package com.geekstudio.nearbite.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeRoute(
    onRestaurantClick: (Restaurant) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val restaurants = state.restaurants.collectAsLazyPagingItems()

    HomeScreen(
        state = state,
        restaurants = restaurants,
        onEvent = viewModel::onEvent,
        onRestaurantClick = onRestaurantClick
    )
}