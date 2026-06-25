package com.geekstudio.nearbite.data.remote.datasource

import android.util.Log
import com.geekstudio.nearbite.data.demo.DemoRestaurants
import com.geekstudio.nearbite.domain.model.Restaurant
import javax.inject.Inject

class DemoRestaurantRemoteDataSource @Inject constructor() : RestaurantRemoteDataSource {

    override suspend fun getNearbyRestaurants(
        latitude: Double,
        longitude: Double,
        query: String
    ): List<Restaurant> {
        Log.d("NearBiteDataSource", "Demo source called with query = $query")

        if (query.isBlank()) {
            return DemoRestaurants.restaurants
        }

        return DemoRestaurants.restaurants.filter { restaurant ->
            restaurant.title.contains(query, ignoreCase = true) ||
                    restaurant.category.contains(query, ignoreCase = true) ||
                    restaurant.address.contains(query, ignoreCase = true)
        }
    }
}