package com.meadowlandapps.clouds.repository

import com.meadowlandapps.clouds.db.ConditionBase
import com.meadowlandapps.clouds.db.Currently
import com.meadowlandapps.clouds.db.Day
import com.meadowlandapps.clouds.db.Hour
import com.meadowlandapps.clouds.db.dao.ForecastDao
import com.meadowlandapps.clouds.timeToDisplayString
import com.meadowlandapps.clouds.toDecimalDisplayString
import com.meadowlandapps.clouds.ui.model.BaseForecastModel
import com.meadowlandapps.clouds.ui.model.CurrentConditionsModel
import com.meadowlandapps.clouds.ui.model.DailyForecastModel
import com.meadowlandapps.clouds.ui.model.HourlyForecastModel
import com.meadowlandapps.clouds.windBearingToWindDirection

class ForecastRepository(private val dao: ForecastDao) {

    val currentConditions = dao.currently
    val hourlyForecast = dao.hourly
    val dailyForecast = dao.daily

    suspend fun getCurrentConditions() =
        mapCurrentlyToCurrentConditionsModel(dao.getCurrently())

    suspend fun getHourlyForecast() = dao.getHourly().map { hourly ->
        mapHourlyToHourlyForecastModel(hourly)
    }

    suspend fun getDailyForecast() = dao.getDaily().map { daily ->
        mapDailyToDailyForecastModel(daily)
    }

    private fun mapCommonProperties(baseCondition: ConditionBase): BaseForecastModel {
        val time = baseCondition.time
        val sky = baseCondition.summary
        val dewPoint = baseCondition.dewPoint
        val humidity = baseCondition.humidity
        val pressure = baseCondition.pressure
        val windSpeed = baseCondition.windSpeed
        val windBearing = baseCondition.windBearing

        val currentTimeDisplayString = time.timeToDisplayString()
        val dewPointDisplayString = dewPoint.toDecimalDisplayString()
        val humidityDisplayString = humidity.toDecimalDisplayString()
        val pressureDisplayString = pressure.toDecimalDisplayString()
        val windSpeedDisplayString = windSpeed.toDecimalDisplayString()
        val windDirection = windBearing.windBearingToWindDirection()

        return BaseForecastModel(
            sky = sky,
            time = currentTimeDisplayString,
            dewPoint = dewPointDisplayString,
            humidity = humidityDisplayString,
            pressure = pressureDisplayString,
            windSpeed = windSpeedDisplayString,
            windDirection = windDirection
        )
    }

    private fun mapCurrentlyToCurrentConditionsModel(currently: Currently): CurrentConditionsModel {
        val baseModel = mapCommonProperties(currently)

        val temperature = currently.temperature
        val temperatureDisplayString = temperature.toDecimalDisplayString()
        val apparentTemperature = currently.apparentTemperature
        val apparentTemperatureDisplayString = apparentTemperature.toDecimalDisplayString()

        return CurrentConditionsModel(
            time = baseModel.time,
            sky = baseModel.sky,
            dewPoint = baseModel.dewPoint,
            humidity = baseModel.humidity,
            pressure = baseModel.pressure,
            windSpeed = baseModel.windSpeed,
            windDirection = baseModel.windDirection,
            temp = "$temperatureDisplayString degrees",
            apparentTemp = "$apparentTemperatureDisplayString degrees"
        )
    }

    private fun mapHourlyToHourlyForecastModel(hourly: Hour): HourlyForecastModel {
        val baseForecastModel = mapCommonProperties(hourly)

        val temperature = hourly.temperature
        val temperatureDisplayString = temperature.toDecimalDisplayString()
        val apparentTemperature = hourly.apparentTemperature
        val apparentTemperatureDisplayString = apparentTemperature.toDecimalDisplayString()

        return HourlyForecastModel(
            baseForecastModel,
            "$temperatureDisplayString degrees",
            "$apparentTemperatureDisplayString degrees"
        )
    }

    private fun mapDailyToDailyForecastModel(daily: Day): DailyForecastModel {
        val baseForecastModel = mapCommonProperties(daily)

        val highTemperature = daily.temperatureHigh
        val highTemperatureDisplayString = highTemperature.toDecimalDisplayString()
        val lowTemperature = daily.temperatureLow
        val lowTemperatureDisplayString = lowTemperature.toDecimalDisplayString()
        val sunriseTime = daily.sunriseTime.timeToDisplayString()
        val sunsetTime = daily.sunsetTime.timeToDisplayString()

        return DailyForecastModel(
            baseForecastModel,
            "$highTemperatureDisplayString degrees",
            "$lowTemperatureDisplayString degrees",
            sunriseTime,
            sunsetTime
        )
    }
}