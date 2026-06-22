package com.geekstudio.nearbite.data.remote.datasource

import com.geekstudio.nearbite.domain.model.Restaurant

interface RestaurantRemoteDataSource {

    suspend fun getNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): List<Restaurant>
}