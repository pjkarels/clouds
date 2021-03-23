package com.meadowlandapps.clouds.db

import androidx.room.PrimaryKey

open class ConditionBase(
    @PrimaryKey val time: Long,
    val summary: String,
    val dewPoint: Double,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val windBearing: Int,
    val cloudCover: Double
) {
}