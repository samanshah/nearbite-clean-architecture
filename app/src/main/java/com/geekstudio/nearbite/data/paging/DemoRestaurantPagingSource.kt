package com.geekstudio.nearbite.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.geekstudio.nearbite.data.demo.DemoRestaurants
import com.geekstudio.nearbite.domain.model.Restaurant

class DemoRestaurantPagingSource : PagingSource<Int, Restaurant>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, Restaurant> {
        return LoadResult.Page(
            data = DemoRestaurants.restaurants,
            prevKey = null,
            nextKey = null
        )
    }

    override fun getRefreshKey(
        state: PagingState<Int, Restaurant>
    ): Int? {
        return null
    }
}