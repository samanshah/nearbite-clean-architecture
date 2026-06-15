package com.geekstudio.nearbite.presentation.home.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.presentation.home.components.RestaurantCard
import com.geekstudio.nearbite.presentation.home.state.HomeUiEvent
import com.geekstudio.nearbite.presentation.home.state.HomeUiState

@Composable
fun HomeScreen(

    state: HomeUiState,

    restaurants: LazyPagingItems<Restaurant>,

    onEvent: (HomeUiEvent) -> Unit,

    onRestaurantClick: (String) -> Unit

) {

    Column {

        OutlinedTextField(

            value = state.searchQuery,

            onValueChange = {

                onEvent(
                    HomeUiEvent.SearchChanged(
                        it
                    )
                )

            },

            modifier = Modifier.fillMaxWidth(),

            label = {
                Text("Search")
            }

        )

        LazyRow {
            items(restaurants.itemCount) {
                Text(restaurants[it]?.name ?: "")
            }
        }

        LazyColumn {

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

        }

    }

}