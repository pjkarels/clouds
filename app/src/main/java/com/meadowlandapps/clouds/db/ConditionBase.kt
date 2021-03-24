package com.meadowlandapps.clouds.db

import androidx.room.PrimaryKey

open class ConditionBase(
    @PrimaryKey val time: Long = 0,
    val summary: String = "",
    val dewPoint: Double = 0.0,
    val humidity: Double = 0.0,
    val pressure: Double = 0.0,
    val windSpeed: Double = 0.0,
    val windBearing: Int = 0,
    val cloudCover: Double = 0.0
) {
}