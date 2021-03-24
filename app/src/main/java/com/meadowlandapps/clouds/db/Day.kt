package com.meadowlandapps.clouds.db

import androidx.room.Entity

@Entity(tableName = "daily_table")
class Day(
    time: Long,
    summary: String,
    val sunriseTime: Long,
    val sunsetTime: Long,
    val temperatureHigh: Double,
    val temperatureLow: Double,
    dewPoint: Double,
    humidity: Double,
    pressure: Double,
    windSpeed: Double,
    windBearing: Int,
    cloudCover: Double
) : ConditionBase(
    time, summary, dewPoint, humidity, pressure, windSpeed, windBearing, cloudCover
)
