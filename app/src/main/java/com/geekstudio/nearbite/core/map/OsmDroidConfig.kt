package com.geekstudio.nearbite.core.map

import android.content.Context
import org.osmdroid.config.Configuration

object OsmDroidConfig {

    fun initialize(
        context: Context
    ) {
        Configuration.getInstance().apply {
            userAgentValue = context.packageName
            load(
                context,
                context.getSharedPreferences(
                    "osmdroid_preferences",
                    Context.MODE_PRIVATE
                )
            )
        }
    }
}