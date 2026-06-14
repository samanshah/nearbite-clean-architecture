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

        latitude = coordinates?.latitude ?: 0.0,

        longitude = coordinates?.longitude ?: 0.0,

        address = location?.address.orEmpty(),

        isFavorite = false

    )

}

fun RestaurantEntity.toDomain(): Restaurant {

    return Restaurant(

        id = id,

        name = name,

        imageUrl = imageUrl,

        rating = rating,

        category = category,

        latitude = latitude,

        longitude = longitude,

        address = address,

        isFavorite = isFavorite

    )

}