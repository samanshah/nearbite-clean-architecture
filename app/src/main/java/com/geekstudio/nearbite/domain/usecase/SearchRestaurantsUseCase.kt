package com.geekstudio.nearbite.domain.usecase

import androidx.paging.PagingData
import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRestaurantsUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

    operator fun invoke(
        latitude: Double,
        longitude: Double,
        query: String
    ): Flow<PagingData<Restaurant>> {
        return repository.getNearbyRestaurants(
            latitude = latitude,
            longitude = longitude,
            query = query
        )
    }
}