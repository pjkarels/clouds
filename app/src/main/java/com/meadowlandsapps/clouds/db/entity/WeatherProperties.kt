package com.meadowlandsapps.clouds.db.entity

class WeatherProperties(
    val updated: String,
    val units: String,
    val updatedTime: String,
    val periods: List<Weather>
)