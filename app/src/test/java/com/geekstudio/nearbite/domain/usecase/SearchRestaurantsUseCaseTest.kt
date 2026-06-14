package com.geekstudio.nearbite.domain.usecase

import app.cash.turbine.test
import com.geekstudio.nearbite.fake.FakeRestaurantRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SearchRestaurantsUseCaseTest {

    @Test
    fun `invoke should return restaurant paging data`() = runTest {

        val useCase = SearchRestaurantsUseCase(
            FakeRestaurantRepository()
        )

        useCase(
            latitude = 35.0, longitude = 51.0
        ).test {

            awaitItem()

            cancelAndIgnoreRemainingEvents()

        }

    }

}