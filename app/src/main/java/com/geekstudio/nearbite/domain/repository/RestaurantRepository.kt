package com.geekstudio.nearbite.domain.repository

import androidx.paging.PagingData
import com.geekstudio.nearbite.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow
import com.geekstudio.nearbite.core.common.Result

interface RestaurantRepository {

    fun getNearbyRestaurants(
        latitude: Double,
        longitude: Double,
        query: String
    ): Flow<PagingData<Restaurant>>

}