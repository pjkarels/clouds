package com.meadowlandapps.clouds.db

//@Entity(tableName = "hour_table")
class Hour(
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
    time, summary, dewPoint, humidity, pressure, windSpeed, windBearing, cloudCover
)
