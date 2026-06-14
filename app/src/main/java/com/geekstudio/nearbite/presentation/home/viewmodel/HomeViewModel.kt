package com.geekstudio.nearbite.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.geekstudio.nearbite.domain.usecase.SearchRestaurantsUseCase
import com.geekstudio.nearbite.presentation.home.state.HomeUiEvent
import com.geekstudio.nearbite.presentation.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchRestaurants: SearchRestaurantsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        HomeUiState()
    )

    val state = _state.asStateFlow()

    init {

        loadRestaurants()

    }

    fun onEvent(
        event: HomeUiEvent
    ) {

        when (event) {

            is HomeUiEvent.SearchChanged -> {

                _state.update {

                    it.copy(
                        searchQuery = event.query
                    )

                }

            }

            HomeUiEvent.Retry -> {

                loadRestaurants()

            }

            is HomeUiEvent.RestaurantClicked -> {

            }

        }

    }

    private fun loadRestaurants() {

        val flow = searchRestaurants(
            latitude = 40.4093, longitude = 49.8671
        ).cachedIn(viewModelScope)

        _state.update {

            it.copy(
                restaurants = flow
            )

        }

    }

}