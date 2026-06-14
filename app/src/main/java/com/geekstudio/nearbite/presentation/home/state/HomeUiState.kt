package com.geekstudio.nearbite.presentation.home.state

import androidx.paging.PagingData
import com.geekstudio.nearbite.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeUiState(

    val restaurants: Flow<PagingData<Restaurant>> = emptyFlow(),

    val isLoading: Boolean = false,

    val error: String? = null,

    val searchQuery: String = ""

)