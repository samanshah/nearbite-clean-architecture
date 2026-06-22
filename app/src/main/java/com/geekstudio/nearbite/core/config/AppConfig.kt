package com.geekstudio.nearbite.core.config

import com.geekstudio.nearbite.BuildConfig

object AppConfig {

    val dataSourceType: DataSourceType
        get() = runCatching {
            DataSourceType.valueOf(BuildConfig.DATA_SOURCE)
        }.getOrDefault(DataSourceType.DEMO)
}