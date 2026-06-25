package com.geekstudio.nearbite.presentation.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.geekstudio.nearbite.domain.usecase.SearchRestaurantsUseCase
import com.geekstudio.nearbite.presentation.home.state.HomeUiEvent
import com.geekstudio.nearbite.presentation.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchRestaurants: SearchRestaurantsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())

    val state = _state.asStateFlow()

    init {
        observeSearchQuery()
        loadRestaurants(query = "")
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.SearchChanged -> {
                _state.update {
                    it.copy(searchQuery = event.query)
                }
            }

            else -> {}
        }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            state
                .map { it.searchQuery }
                .debounce(400)
                .distinctUntilChanged()
                .collect { query ->
                    loadRestaurants(query = query)
                }
        }
    }

    private fun loadRestaurants(
        query: String
    ) {
        val restaurants = searchRestaurants(
            latitude = DEFAULT_LATITUDE,
            longitude = DEFAULT_LONGITUDE,
            query = query
        ).cachedIn(viewModelScope)

        _state.update {
            it.copy(
                restaurants = restaurants
            )
        }
    }

    private companion object {
        const val DEFAULT_LATITUDE = 40.4093
        const val DEFAULT_LONGITUDE = 49.8671
    }
}