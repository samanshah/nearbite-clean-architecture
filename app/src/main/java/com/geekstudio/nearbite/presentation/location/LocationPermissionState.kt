package com.geekstudio.nearbite.presentation.location

sealed interface LocationPermissionState {

    data object Granted : LocationPermissionState

    data object Denied : LocationPermissionState

    data object PermanentlyDenied : LocationPermissionState

}