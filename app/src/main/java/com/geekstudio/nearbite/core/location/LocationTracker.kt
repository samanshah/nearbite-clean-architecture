package com.geekstudio.nearbite.core.location

import android.location.Location

interface LocationTracker {

    suspend fun getCurrentLocation(): Location?

}