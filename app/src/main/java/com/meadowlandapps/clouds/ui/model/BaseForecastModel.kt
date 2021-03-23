package com.meadowlandapps.clouds.ui.model

open class BaseForecastModel(
    val time: String,
    /**
     * E.g. Sunny, Cloudy, Raining, etc.
     */
    val sky: String,
    val dewPoint: String,
    val windSpeed: String,
    val windDirection: String,
    val pressure: String,
    val humidity: String,
) {

}