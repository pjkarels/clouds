package com.meadowlandapps.clouds.db

import androidx.room.Entity

@Entity(tableName = "currently_table")
class Currently(
    time: Long,
    summary: String,
    val temperature: Double = 0.0,
    val apparentTemperature: Double = 0.0,
    dewPoint: Double,
    humidity: Double,
    pressure: Double,
    windSpeed: Double,
    windBearing: Int,
    cloudCover: Double
) : ConditionBase(
    time, summary, dewPoint, humidity, pressure, windSpeed, windBearing, cloudCover
)