package com.geekstudio.nearbite.presentation.home.ui

import android.Manifest
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.geekstudio.nearbite.presentation.home.viewmodel.HomeViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeRoute(
    onRestaurantClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val permissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    LaunchedEffect(Unit) {

        permissionState.launchPermissionRequest()

    }

    val state by viewModel.state.collectAsState()

    val restaurants = state.restaurants.collectAsLazyPagingItems()

    HomeScreen(
        state = state,
        restaurants = restaurants,
        onEvent = viewModel::onEvent,
        onRestaurantClick = onRestaurantClick
    )

}