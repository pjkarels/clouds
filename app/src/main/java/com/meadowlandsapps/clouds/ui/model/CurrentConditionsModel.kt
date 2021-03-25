package com.meadowlandsapps.clouds.ui.model

class CurrentConditionsModel(
    baseForecastModel: BaseForecastModel,
    val time: String = "",
) : BaseForecastModel(
    baseForecastModel.sky,
    baseForecastModel.temp,
    baseForecastModel.tempUnit,
    baseForecastModel.windSpeed,
    baseForecastModel.windDirection
)