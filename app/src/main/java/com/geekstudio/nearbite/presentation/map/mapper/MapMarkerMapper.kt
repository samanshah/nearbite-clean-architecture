package com.geekstudio.nearbite.presentation.map.mapper

import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.presentation.map.model.MapMarkerUiModel

fun Restaurant.toMapMarkerUiModel(): MapMarkerUiModel? {
    if (latitude == 0.0 || longitude == 0.0) {
        return null
    }

    return MapMarkerUiModel(
        id = id,
        title = title,
        latitude = latitude,
        longitude = longitude
    )
}