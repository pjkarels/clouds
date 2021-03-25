package com.meadowlandsapps.clouds.ui.model

class HourlyForecastModel(
    baseForecastModel: BaseForecastModel,
    val time: String = "",
) : BaseForecastModel(
    baseForecastModel.sky,
    baseForecastModel.temp,
    baseForecastModel.tempUnit,
    baseForecastModel.windSpeed,
    baseForecastModel.windDirection
)