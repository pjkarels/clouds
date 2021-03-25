package com.meadowlandapps.clouds.db.entity

import androidx.room.Entity

@Entity(tableName = "hour_table")
class Hour(
    number: Int,
    name: String,
    startTime: String,
    isDaytime: Boolean,
    temperature: Int,
    temperatureUnit: String,
    windSpeed: String,
    windDirection: String,
    shortForecast: String,
    detailedForecast: String,
    icon: String
) : Weather(
    number,
    name,
    startTime,
    isDaytime,
    temperature,
    temperatureUnit,
    windSpeed,
    windDirection,
    shortForecast,
    detailedForecast,
    icon
)