package com.meadowlandapps.clouds.ui.model

class HourlyForecastModel(
    sky: String,
    highTemp: Int,
    lowTemp: Int,
    dewPoint: Int,
    windSpeed: Double,
    windDirection: String,
    pressure: Int,
    humidity: Int
): ForecastModel(
    sky, highTemp, lowTemp, dewPoint, windSpeed, windDirection, pressure, humidity
) {
}