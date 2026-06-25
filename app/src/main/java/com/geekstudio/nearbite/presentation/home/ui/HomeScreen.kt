package com.geekstudio.nearbite.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.geekstudio.nearbite.presentation.home.components.RestaurantBottomSheet
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.presentation.home.components.HomeEmptyContent
import com.geekstudio.nearbite.presentation.home.components.HomeErrorContent
import com.geekstudio.nearbite.presentation.home.components.HomeHeader
import com.geekstudio.nearbite.presentation.home.components.HomeLoadingContent
import com.geekstudio.nearbite.presentation.home.components.RestaurantCard
import com.geekstudio.nearbite.presentation.home.components.RestaurantCardPlaceholder
import com.geekstudio.nearbite.presentation.home.components.RestaurantMap
import com.geekstudio.nearbite.presentation.home.components.SearchSection
import com.geekstudio.nearbite.presentation.home.state.HomeUiEvent
import com.geekstudio.nearbite.presentation.home.state.HomeUiState
import com.geekstudio.nearbite.presentation.map.mapper.toMapMarkerUiModel

@Composable
fun HomeScreen(
    state: HomeUiState,
    restaurants: LazyPagingItems<Restaurant>,
    onEvent: (HomeUiEvent) -> Unit,
    onRestaurantClick: (Restaurant) -> Unit
) {

    var selectedRestaurant by remember {
        mutableStateOf<Restaurant?>(null)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        when (val refreshState = restaurants.loadState.refresh) {

            is LoadState.Loading -> {
                HomeLoadingContent()
            }

            is LoadState.Error -> {
                HomeErrorContent(
                    message = refreshState.error.message ?: "Unable to load restaurants",
                    onRetry = {
                        restaurants.retry()
                    }
                )
            }

            is LoadState.NotLoading -> {
                if (restaurants.itemCount == 0) {
                    HomeEmptyContent()
                } else {
                    RestaurantContent(
                        state = state,
                        restaurants = restaurants,
                        onRestaurantClick = onRestaurantClick
                    )
                }
            }
        }

        selectedRestaurant?.let { restaurant ->
            RestaurantBottomSheet(
                restaurant = restaurant,
                onDismiss = {
                    selectedRestaurant = null
                },
                onViewDetailsClick = { restaurant ->
                    selectedRestaurant = null
                    onRestaurantClick(restaurant)
                }
            )
        }
    }
}

@Composable
private fun RestaurantContent(
    state: HomeUiState,
    restaurants: LazyPagingItems<Restaurant>,
    onRestaurantClick: (Restaurant) -> Unit
) {
    val mapMarkers = buildList {
        val count = minOf(
            restaurants.itemCount,
            10
        )

        for (index in 0 until count) {
            restaurants[index]
                ?.toMapMarkerUiModel()
                ?.let {
                    add(it)
                }
        }
    }

    HomeHeader()

    SearchSection(query = state.searchQuery) {

    }

    RestaurantMap(
        markers = mapMarkers
    )

    Text(
        text = "Nearby Restaurants"
    )

    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = restaurants.itemCount,
            key = restaurants.itemKey {
                it.id
            }
        ) { index ->

            restaurants[index]?.let { restaurant ->
                RestaurantCard(
                    restaurant = restaurant,
                    onClick = {
                        onRestaurantClick(restaurant)
                    }
                )
            }
        }

        when (restaurants.loadState.append) {

            is LoadState.Loading -> {
                item {
                    RestaurantCardPlaceholder()
                }
            }

            is LoadState.Error -> {
                item {
                    HomeErrorContent(
                        message = "Unable to load more restaurants",
                        onRetry = {
                            restaurants.retry()
                        }
                    )
                }
            }

            is LoadState.NotLoading -> Unit
        }
    }
}