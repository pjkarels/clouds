package com.meadowlandapps.clouds.ui.model

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

class DailyForecastModel(
    baseForecastModel: BaseForecastModel = BaseForecastModel(),
    val time: String = ""
) : BaseForecastModel(
    baseForecastModel.sky,
    baseForecastModel.temp,
    baseForecastModel.tempUnit,
    baseForecastModel.windSpeed,
    baseForecastModel.windDirection
) {
    @SuppressLint("SimpleDateFormat")
    fun date() {
        val dateFormatter = SimpleDateFormat("MMM d")
        val date = dateFormatter.parse(time)
        dateFormatter.format(date)
    }
}