package com.geekstudio.nearbite.domain.usecase

import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveFavoritesUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

    operator fun invoke(): Flow<List<Restaurant>> {

        return repository.getFavoriteRestaurants()

    }

}