package com.geekstudio.nearbite.core.startup

import android.content.Context
import androidx.startup.Initializer

class AnalyticsInitializer : Initializer<Unit> {

    override fun create(
        context: Context
    ) {

    }

    override fun dependencies(): List<Class<out Initializer<*>>> {

        return emptyList()

    }

}