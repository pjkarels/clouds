package com.meadowlandapps.clouds.ui.model

class DailyForecastModel(
    baseForecastModel: BaseForecastModel,
    val highTemp: String,
    val lowTemp: String,
    val sunriseTime: String,
    val sunsetTime: String
) : BaseForecastModel(
    time = baseForecastModel.time,
    sky = baseForecastModel.sky,
    dewPoint = baseForecastModel.dewPoint,
    humidity = baseForecastModel.humidity,
    pressure = baseForecastModel.pressure,
    windSpeed = baseForecastModel.windSpeed,
    windDirection = baseForecastModel.windDirection
) {
}