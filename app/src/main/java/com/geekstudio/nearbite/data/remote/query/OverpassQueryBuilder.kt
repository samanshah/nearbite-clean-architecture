package com.geekstudio.nearbite.data.remote.query

object OverpassQueryBuilder {

    fun nearbyRestaurants(
        latitude: Double,
        longitude: Double,
        radiusMeters: Int = DEFAULT_RADIUS_METERS
    ): String {
        return """
            [out:json][timeout:25];
            (
              node["amenity"="restaurant"](around:$radiusMeters,$latitude,$longitude);
              node["amenity"="cafe"](around:$radiusMeters,$latitude,$longitude);
              node["amenity"="fast_food"](around:$radiusMeters,$latitude,$longitude);
            );
            out body 50;
        """.trimIndent()
    }

    private const val DEFAULT_RADIUS_METERS = 1500
}