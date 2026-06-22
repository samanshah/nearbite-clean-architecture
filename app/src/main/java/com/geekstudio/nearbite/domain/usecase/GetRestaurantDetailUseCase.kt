package com.geekstudio.nearbite.domain.usecase

import com.geekstudio.nearbite.domain.model.Restaurant
import com.geekstudio.nearbite.domain.repository.RestaurantRepository
import javax.inject.Inject
import com.geekstudio.nearbite.core.common.Result

class GetRestaurantDetailUseCase @Inject constructor(
    private val repository: RestaurantRepository
) {

//    suspend operator fun invoke(
//        restaurantId: String
//    ): Result<Restaurant> {
//
//        return repository
//            .getRestaurantDetail(
//                restaurantId
//            )
//
//    }

}