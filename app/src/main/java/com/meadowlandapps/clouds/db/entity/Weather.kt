package com.meadowlandapps.clouds.db.entity

import androidx.room.PrimaryKey

open class Weather(
    @PrimaryKey val number: Int,
    val name: String,
    val startTime: String,
    val isDaytime: Boolean,
    val temperature: Int,
    val temperatureUnit: String,
    val windSpeed: String,
    val windDirection: String,
    val shortForecast: String,
    val detailedForecast: String,
    val icon: String
)