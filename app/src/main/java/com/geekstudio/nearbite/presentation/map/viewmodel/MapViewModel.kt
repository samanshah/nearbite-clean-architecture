package com.geekstudio.nearbite.presentation.map.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geekstudio.nearbite.core.location.LocationTracker
import com.geekstudio.nearbite.presentation.map.state.MapUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationTracker: LocationTracker
) : ViewModel() {

    private val _state = MutableStateFlow(MapUiState())

    val state = _state.asStateFlow()

    fun loadLocation() {

        viewModelScope.launch {

            val location = locationTracker.getCurrentLocation()

            _state.update {

                it.copy(
                    latitude = location?.latitude, longitude = location?.longitude
                )

            }

        }

    }

}