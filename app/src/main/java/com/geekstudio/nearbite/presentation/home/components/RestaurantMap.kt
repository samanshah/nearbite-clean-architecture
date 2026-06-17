package com.geekstudio.nearbite.presentation.home.components

import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.geekstudio.nearbite.presentation.map.model.MapMarkerUiModel
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@Composable
fun RestaurantMap(
    markers: List<MapMarkerUiModel>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val mapView = remember {
        MapView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)

            controller.setZoom(DEFAULT_ZOOM)
            controller.setCenter(DEFAULT_CENTER)
        }
    }

    DisposableEffect(Unit) {
        mapView.onResume()

        onDispose {
            mapView.onPause()
        }
    }

    AndroidView(
        modifier = modifier
            .fillMaxWidth()
            .height(260.dp),
        factory = {
            mapView
        },
        update = { view ->
            view.overlays.clear()

            val validMarkers = markers.filter {
                it.latitude != 0.0 && it.longitude != 0.0
            }

            validMarkers.forEach { markerModel ->
                val geoPoint = GeoPoint(
                    markerModel.latitude,
                    markerModel.longitude
                )

                val marker = Marker(view).apply {
                    position = geoPoint
                    title = markerModel.title
                    setAnchor(
                        Marker.ANCHOR_CENTER,
                        Marker.ANCHOR_BOTTOM
                    )
                }

                view.overlays.add(marker)
            }

            val firstMarker = validMarkers.firstOrNull()

            if (firstMarker != null) {
                view.controller.animateTo(
                    GeoPoint(
                        firstMarker.latitude,
                        firstMarker.longitude
                    )
                )
            }

            view.invalidate()
        }
    )
}

private const val DEFAULT_ZOOM = 13.0

private val DEFAULT_CENTER = GeoPoint(
    35.6892,
    51.3890
)