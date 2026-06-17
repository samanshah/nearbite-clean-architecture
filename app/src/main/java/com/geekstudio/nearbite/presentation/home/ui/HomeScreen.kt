package com.geekstudio.nearbite.presentation.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.presentation.home.components.RestaurantCard
import com.geekstudio.nearbite.presentation.home.state.HomeUiEvent
import com.geekstudio.nearbite.presentation.home.state.HomeUiState
import com.geekstudio.nearbite.presentation.home.components.RestaurantMap
import com.geekstudio.nearbite.presentation.map.mapper.toMapMarkerUiModel

@Composable
fun HomeScreen(
    state: HomeUiState,
    restaurants: LazyPagingItems<Restaurant>,
    onEvent: (HomeUiEvent) -> Unit,
    onRestaurantClick: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                onEvent(HomeUiEvent.SearchChanged(it))
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Search restaurants")
            },
            singleLine = true
        )

        when (val refreshState = restaurants.loadState.refresh) {

            is LoadState.Loading -> {
                LoadingContent()
            }

            is LoadState.Error -> {
                ErrorContent(
                    message = refreshState.error.message ?: "Unable to load restaurants",
                    onRetry = {
                        restaurants.retry()
                    }
                )
            }

            is LoadState.NotLoading -> {
                if (restaurants.itemCount == 0) {
                    EmptyContent()
                } else {
                    RestaurantContent(
                        restaurants = restaurants,
                        onRestaurantClick = onRestaurantClick
                    )
                }
            }
        }
    }
}

@Composable
private fun RestaurantContent(
    restaurants: LazyPagingItems<Restaurant>,
    onRestaurantClick: (String) -> Unit
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
    RestaurantMap(
        markers = mapMarkers
    )
    LazyRow(
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(restaurants.itemCount) { index ->
            Text(
                text = restaurants[index]?.title.orEmpty()
            )
        }
    }

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
                        onRestaurantClick(restaurant.id)
                    }
                )
            }
        }

        when (restaurants.loadState.append) {

            is LoadState.Loading -> {
                item {
                    CircularProgressIndicator()
                }
            }

            is LoadState.Error -> {
                item {
                    Button(
                        onClick = {
                            restaurants.retry()
                        }
                    ) {
                        Text("Retry loading more")
                    }
                }
            }

            is LoadState.NotLoading -> Unit
        }
    }
}

@Composable
private fun LoadingContent() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorContent(
    message: String,
    onRetry: () -> Unit
) {
    Column {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error
        )

        Button(
            onClick = onRetry
        ) {
            Text("Retry")
        }
    }
}

@Composable
private fun EmptyContent() {
    Text("No restaurants found")
}