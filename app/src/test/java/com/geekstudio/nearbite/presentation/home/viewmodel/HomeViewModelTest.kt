package com.geekstudio.nearbite.presentation.home.viewmodel

import com.geekstudio.nearbite.domain.usecase.SearchRestaurantsUseCase
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun `initial state should load restaurants`() = runTest {

        val useCase = mockk<SearchRestaurantsUseCase>()

    }

}