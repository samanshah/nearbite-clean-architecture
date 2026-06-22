package com.geekstudio.nearbite.data.mapper

import com.geekstudio.nearbite.data.remote.dto.OverpassElementDto
import com.geekstudio.nearbite.domain.model.Restaurant
import kotlin.math.abs

fun OverpassElementDto.toRestaurantOrNull(): Restaurant? {
    val restaurantId = id?.toString() ?: return null
    val latitude = lat ?: center?.lat ?: return null
    val longitude = lon ?: center?.lon ?: return null

    val title = tags?.name
        ?.takeIf { it.isNotBlank() }
        ?: fallbackName(
            amenity = tags?.amenity,
            id = restaurantId
        )

    return Restaurant(
        id = restaurantId,
        title = title,
        imageUrl = "",
        rating = fallbackRating(restaurantId),
        category = tags?.cuisine
            ?.takeIf { it.isNotBlank() }
            ?: tags?.amenity
                ?.takeIf { it.isNotBlank() }
            ?: "Restaurant",
        latitude = latitude,
        longitude = longitude,
        address = buildAddress(),
        isFavorite = false
    )
}

private fun OverpassElementDto.buildAddress(): String {
    val street = tags?.street.orEmpty()
    val houseNumber = tags?.houseNumber.orEmpty()
    val city = tags?.city.orEmpty()

    return listOf(street, houseNumber, city)
        .filter { it.isNotBlank() }
        .joinToString(", ")
        .ifBlank { "Address not available" }
}

private fun fallbackName(
    amenity: String?,
    id: String
): String {
    val label = when (amenity) {
        "cafe" -> "Cafe"
        "fast_food" -> "Fast Food"
        else -> "Restaurant"
    }

    return "$label #$id"
}

private fun fallbackRating(
    id: String
): Double {
    val normalized = abs(id.hashCode() % 15)
    return 3.5 + normalized / 10.0
}