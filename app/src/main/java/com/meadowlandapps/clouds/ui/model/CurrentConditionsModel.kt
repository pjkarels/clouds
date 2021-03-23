package com.meadowlandapps.clouds.ui.model

class CurrentConditionsModel(
    val baseForecastModel: BaseForecastModel,
    val temp: String = "",
    val apparentTemp: String = ""
) : BaseForecastModel(
    time = baseForecastModel.time,
    sky = baseForecastModel.sky,
    dewPoint = baseForecastModel.dewPoint,
    humidity = baseForecastModel.humidity,
    pressure = baseForecastModel.pressure,
    windSpeed = baseForecastModel.windSpeed,
    windDirection = baseForecastModel.windDirection
)