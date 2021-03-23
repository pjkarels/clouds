package com.meadowlandapps.clouds.db

import androidx.room.Entity

@Entity(tableName = "currently_table")
class Currently(
    time: Long,
    summary: String,
    val temperature: Double,
    val apparentTemperature: Double,
    dewPoint: Double,
    humidity: Double,
    pressure: Double,
    windSpeed: Double,
    windBearing: Int,
    cloudCover: Double
) : ConditionBase(
    time = time,
    summary = summary,
    dewPoint = dewPoint,
    humidity = humidity,
    pressure = pressure,
    windSpeed = windSpeed,
    windBearing = windBearing,
    cloudCover = cloudCover
) {
}