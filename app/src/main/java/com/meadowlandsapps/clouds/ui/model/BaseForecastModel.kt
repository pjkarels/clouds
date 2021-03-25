package com.meadowlandsapps.clouds.ui.model

open class BaseForecastModel(
    /**
     * E.g. Sunny, Cloudy, Raining, etc.
     */
    val sky: String = "",
    val temp: String = "",
    val tempUnit: String = "",
    val windSpeed: String = "",
    val windDirection: String = "",
) {
    fun temperature() = "$temp $tempUnit"
}