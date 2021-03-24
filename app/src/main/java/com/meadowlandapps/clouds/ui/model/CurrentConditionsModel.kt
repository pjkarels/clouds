package com.meadowlandapps.clouds.ui.model

class CurrentConditionsModel(
    val time: String = "",
    /**
     * E.g. Sunny, Cloudy, Raining, etc.
     */
    val sky: String = "",
    val dewPoint: String = "",
    val windSpeed: String = "",
    val windDirection: String = "",
    val pressure: String = "",
    val humidity: String = "",
    val temp: String = "",
    val apparentTemp: String = ""
)