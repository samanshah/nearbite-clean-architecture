package com.geekstudio.nearbite.domain.usecase

import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

    suspend operator fun invoke(
        restaurantId: String
    ) {

        repository.toggleFavorite(
            restaurantId
        )

    }

}