package com.meadowlandapps.clouds.repository

import android.util.Log
import androidx.lifecycle.map
import com.meadowlandapps.clouds.NOAAService
import com.meadowlandapps.clouds.db.dao.ForecastDao
import com.meadowlandapps.clouds.db.entity.Current
import com.meadowlandapps.clouds.db.entity.Day
import com.meadowlandapps.clouds.db.entity.Forecast
import com.meadowlandapps.clouds.db.entity.Hour
import com.meadowlandapps.clouds.db.entity.Point
import com.meadowlandapps.clouds.db.entity.Weather
import com.meadowlandapps.clouds.ui.model.BaseForecastModel
import com.meadowlandapps.clouds.ui.model.CurrentConditionsModel
import com.meadowlandapps.clouds.ui.model.DailyForecastModel
import com.meadowlandapps.clouds.ui.model.HourlyForecastModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory

class ForecastRepository(private val dao: ForecastDao) {

    private val noaaService: NOAAService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weather.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        noaaService = retrofit.create(NOAAService::class.java)
    }

    suspend fun fetchPoints(lat: Double, long: Double): Response<Point> {
        Log.d(ForecastRepository::class.simpleName, "Fetching Points")
        return noaaService.getPoints("points/$lat,$long").awaitResponse()
    }

    suspend fun fetchForecast(url: String): Boolean {
        Log.d(ForecastRepository::class.simpleName, "Fetching Daily Forecast")
        val response = noaaService.getForecast(url).awaitResponse()
        val successful = response.isSuccessful && response.body() != null
        if (successful) {
            Log.d(ForecastRepository::class.simpleName, "Success Fetching Daily Forecast")
            val forecast = response.body() as Forecast
            val weather = forecast.properties.periods[0]
            // transform data to fit into DB schema
            dao.insertCurrently(
                Current(
                    weather.number,
                    weather.name,
                    weather.startTime,
                    weather.isDaytime,
                    weather.temperature,
                    weather.temperatureUnit,
                    weather.windSpeed,
                    weather.windDirection,
                    weather.shortForecast,
                    weather.detailedForecast,
                    weather.icon
                )
            )
            // transform data to fit into DB schema
            val daily = forecast.properties.periods.map { dailyWeather ->
                Day(
                    dailyWeather.number,
                    dailyWeather.name,
                    dailyWeather.startTime,
                    dailyWeather.isDaytime,
                    dailyWeather.temperature,
                    dailyWeather.temperatureUnit,
                    dailyWeather.windSpeed,
                    dailyWeather.windDirection,
                    dailyWeather.shortForecast,
                    dailyWeather.detailedForecast,
                    dailyWeather.icon
                )
            }
            dao.insertDaily(daily)
        }

        return response.isSuccessful
    }

    suspend fun fetchHourlyForecast(url: String): Boolean {
        Log.d(ForecastRepository::class.simpleName, "Fetching Hourly Forecast")
        val response = noaaService.getForecast(url).awaitResponse()
        val successful = response.isSuccessful && response.body() != null
        if (successful) {
            Log.d(ForecastRepository::class.simpleName, "Success Fetching Hourly Forecast")
            val forecast = response.body() as Forecast
            val hourly = forecast.properties.periods.map { hourlyWeather ->
                Hour(
                    hourlyWeather.number,
                    hourlyWeather.name,
                    hourlyWeather.startTime,
                    hourlyWeather.isDaytime,
                    hourlyWeather.temperature,
                    hourlyWeather.temperatureUnit,
                    hourlyWeather.windSpeed,
                    hourlyWeather.windDirection,
                    hourlyWeather.shortForecast,
                    hourlyWeather.detailedForecast,
                    hourlyWeather.icon
                )
            }
            dao.insertHourly(hourly)
        }

        return response.isSuccessful
    }

    val currentConditions = dao.current.map { current ->
        mapCurrentlyToCurrentConditionsModel(current)
    }

    val hourlyForecast = dao.hourly.map { hourly ->
        hourly.map { hour ->
            mapHourlyToHourlyForecastModel(hour)
        }
    }

    val dailyForecast = dao.daily.map { daily ->
        daily.map { day ->
            mapDailyToDailyForecastModel(day)
        }
    }

    private fun mapHourlyToHourlyForecastModel(hourly: Hour?): HourlyForecastModel {
        val baseForecastModel = mapCommonProperties(hourly)
        return if (hourly == null) {
            HourlyForecastModel(
                baseForecastModel
            )
        } else {
            HourlyForecastModel(
                baseForecastModel,
                hourly.startTime
            )
        }
    }

    private fun mapDailyToDailyForecastModel(daily: Day?): DailyForecastModel {
        val baseForecastModel = mapCommonProperties(daily)
        return if (daily == null) {
            DailyForecastModel(
                baseForecastModel
            )
        } else {
            DailyForecastModel(
                baseForecastModel,
                daily.startTime
            )
        }
    }

    private fun mapCurrentlyToCurrentConditionsModel(current: Current?): CurrentConditionsModel {
        val baseForecastModel = mapCommonProperties(current)
        return if (current == null) {
            CurrentConditionsModel(
                baseForecastModel
            )
        } else {
            CurrentConditionsModel(
                baseForecastModel,
                current.startTime
            )
        }
    }

    private fun mapCommonProperties(weather: Weather?): BaseForecastModel {
        return if (weather == null) {
            BaseForecastModel()
        } else {
            BaseForecastModel(
                sky = weather.shortForecast,
                temp = weather.temperature.toString(),
                tempUnit = weather.temperatureUnit,
                windSpeed = weather.windSpeed,
                windDirection = weather.windDirection
            )
        }
    }
}