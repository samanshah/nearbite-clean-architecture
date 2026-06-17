package com.geekstudio.nearbite.data.mapper

import com.geekstudio.nearbite.data.local.entity.RestaurantEntity
import com.geekstudio.nearbite.data.remote.dto.RestaurantDto
import com.geekstudio.nearbite.domain.model.Restaurant

fun RestaurantDto.toEntity(): RestaurantEntity {

    return RestaurantEntity(

        id = id,

        name = name,

        imageUrl = imageUrl.orEmpty(),

        rating = rating ?: 0.0,

        category = categories?.firstOrNull()?.title.orEmpty(),

        latitude = coordinates?.latitude ?: fallbackLatitude(id),

        longitude = coordinates?.longitude ?: fallbackLongitude(id),

        address = location?.address.orEmpty(),

        isFavorite = false

    )

}

fun RestaurantEntity.toDomain(): Restaurant {

    return Restaurant(

        id = id,

        title = name,

        imageUrl = imageUrl,

        rating = rating,

        category = category,

        latitude = latitude,

        longitude = longitude,

        address = address,

        isFavorite = isFavorite

    )

}

private fun fallbackLatitude(
    id: String
): Double {
    return 35.6892 + (id.hashCode() % 100) * 0.0001
}

private fun fallbackLongitude(
    id: String
): Double {
    return 51.3890 + (id.hashCode() % 100) * 0.0001
}