package com.meadowlandapps.clouds.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_table")
data class Day(
    @PrimaryKey val time: Long,
    val summary: String,
    val sunriseTime: Long,
    val sunsetTime: Long,
    val temperatureHigh: Double,
    val temperatureLow: Double,
    val dewPoint: Double,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val windBearing: Int,
    val cloudCover: Double
)
