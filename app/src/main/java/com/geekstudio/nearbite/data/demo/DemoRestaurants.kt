package com.geekstudio.nearbite.data.demo

import com.geekstudio.nearbite.domain.model.Restaurant

object DemoRestaurants {

    val restaurants = listOf(
        Restaurant(
            id = "1",
            title = "Tehran Burger House",
            imageUrl = "",
            rating = 4.4,
            category = "Fast Food",
            latitude = 35.7001,
            longitude = 51.4002,
            address = "Valiasr Street, Tehran",
            isFavorite = false
        ),
        Restaurant(
            id = "2",
            title = "Persian Taste",
            imageUrl = "",
            rating = 4.7,
            category = "Persian",
            latitude = 35.7054,
            longitude = 51.3948,
            address = "Keshavarz Blvd, Tehran",
            isFavorite = false
        ),
        Restaurant(
            id = "3",
            title = "Cafe NearBite",
            imageUrl = "",
            rating = 4.2,
            category = "Cafe",
            latitude = 35.6978,
            longitude = 51.4101,
            address = "Karimkhan Street, Tehran",
            isFavorite = false
        )
    )
}