package com.meadowlandapps.clouds.ui.model

class CurrentConditionsModel(
    val sky: String,
    val temp: Int,
    val humidity: Int,
    val pressure: Double,
    val windSpeed: Int,
    val windDirection: String
) {
}