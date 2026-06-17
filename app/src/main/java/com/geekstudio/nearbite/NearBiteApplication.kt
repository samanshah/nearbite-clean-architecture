package com.geekstudio.nearbite

import android.app.Application
import com.geekstudio.nearbite.core.map.OsmDroidConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NearBiteApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        OsmDroidConfig.initialize(
            context = this
        )
    }
}