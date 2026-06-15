package com.geekstudio.nearbite.presentation.home.components

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*

@Composable
fun RestaurantMap(

    latitude: Double,

    longitude: Double

) {

    val cameraPositionState =
        rememberCameraPositionState {

            position =
                CameraPosition.fromLatLngZoom(
                    LatLng(
                        latitude,
                        longitude
                    ),
                    14f
                )

        }

    val state = rememberMarkerState()

    GoogleMap(
        cameraPositionState =
            cameraPositionState
    ) {

        Marker(
            state = state
        )

    }

}