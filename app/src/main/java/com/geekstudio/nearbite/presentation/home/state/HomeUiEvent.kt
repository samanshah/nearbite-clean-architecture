package com.geekstudio.nearbite.presentation.home.state

sealed interface HomeUiEvent {

    data class SearchChanged(
        val query: String
    ) : HomeUiEvent

    data object Retry :
        HomeUiEvent

    data class RestaurantClicked(
        val restaurantId: String
    ) : HomeUiEvent

}