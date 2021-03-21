package com.meadowlandapps.clouds.ui.model

class DailyForecastModel(
    sky: String,
    highTemp: Int,
    lowTemp: Int,
    dewPoint: Int,
    windSpeed: Double,
    windDirection: String,
    pressure: Int,
    humidity: Int,
    val sunriseTime: Long,
    val sunsetTime: Long
): ForecastModel(
    sky, highTemp, lowTemp, dewPoint, windSpeed, windDirection, pressure, humidity
) {
}