package com.meadowlandapps.clouds.ui.model

open class ForecastModel(
    /**
     * E.g. Sunny, Cloudy, Raining, etc.
     */
    val sky: String,
    val highTemp: Int,
    val lowTemp: Int,
    val dewPoint: Int,
    val windSpeed: Double,
    val windDirection: String,
    val pressure: Int,
    val humidity: Int,
) {
}