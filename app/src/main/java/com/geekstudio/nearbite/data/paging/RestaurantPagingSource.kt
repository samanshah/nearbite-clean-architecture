package com.geekstudio.nearbite.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geekstudio.nearbite.data.remote.api.RestaurantApi
import com.geekstudio.nearbite.domain.model.Restaurant

class RestaurantPagingSource(
    private val api: RestaurantApi,
    private val latitude: Double,
    private val longitude: Double
) : PagingSource<Int, Restaurant>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Restaurant> {

        return try {

            val offset = params.key ?: 0

            val response = api.searchRestaurants(
                latitude = latitude, longitude = longitude, limit = 20, offset = offset
            )

            LoadResult.Page(

                data = response.restaurants.map {

                    Restaurant(
                        id = it.id,
                        title = it.name,
                        imageUrl = it.imageUrl.orEmpty(),
                        rating = it.rating ?: 0.0,
                        category = it.categories?.firstOrNull()?.title.orEmpty(),
                        latitude = it.coordinates?.latitude ?: 0.0,
                        longitude = it.coordinates?.longitude ?: 0.0,
                        address = it.location?.address.orEmpty(),
                        isFavorite = false
                    )

                },

                prevKey = null,

                nextKey = offset + 20

            )

        } catch (e: Exception) {

            LoadResult.Error(e)

        }

    }

    override fun getRefreshKey(
        state: PagingState<Int, Restaurant>
    ): Int? = null

}