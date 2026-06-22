package com.geekstudio.nearbite.data.remote.datasource

import android.util.Log
import com.geekstudio.nearbite.data.demo.DemoRestaurants
import com.geekstudio.nearbite.domain.model.Restaurant
import javax.inject.Inject

class DemoRestaurantRemoteDataSource @Inject constructor() : RestaurantRemoteDataSource {

    override suspend fun getNearbyRestaurants(
        latitude: Double,
        longitude: Double
    ): List<Restaurant> {
        Log.d("NearBiteDataSource", "Demo source called")

        return DemoRestaurants.restaurants
    }
}