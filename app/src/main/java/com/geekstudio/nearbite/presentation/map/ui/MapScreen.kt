package com.geekstudio.nearbite.presentation.map.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.geekstudio.nearbite.presentation.map.state.MapUiState
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(
    state: MapUiState
) {

    if (
        state.latitude == null ||
        state.longitude == null
    ) {
//        LoadingView() //todo
        return
    }

    val position =
        LatLng(
            state.latitude,
            state.longitude
        )

    val state = rememberMarkerState()
    state.position = position

    GoogleMap(

        modifier = Modifier.fillMaxSize()

    ) {

        Marker(

            state = state,

            title = "Current Location"

        )

    }

}