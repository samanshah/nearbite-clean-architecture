package com.geekstudio.nearbite.fake

import androidx.paging.*
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import com.geekstudio.nearbite.core.common.Result

class FakeRestaurantRepository : RestaurantRepository {

    override fun getNearbyRestaurants(
        latitude: Double, longitude: Double
    ): Flow<PagingData<Restaurant>> {

        return flowOf(
            PagingData.from(
                listOf(
                    Restaurant(
                        id = "1",
                        title = "Pizza Place",
                        imageUrl = "",
                        rating = 4.5,
                        category = "Italian",
                        latitude = latitude,
                        longitude = longitude,
                        address = "Tehran",
                        isFavorite = false
                    )
                )
            )
        )

    }

    override suspend fun getRestaurantDetail(
        id: String
    ): Result<Restaurant> {

        return Result.Success(
            Restaurant(
                id = id,
                title = "Pizza Place",
                imageUrl = "",
                rating = 4.5,
                category = "Italian",
                latitude = 0.0,
                longitude = 0.0,
                address = "Tehran",
                isFavorite = false
            )
        )

    }

    override suspend fun toggleFavorite(
        restaurantId: String
    ) {

    }

    override fun getFavoriteRestaurants(): Flow<List<Restaurant>> {

        return flowOf(emptyList())

    }

}