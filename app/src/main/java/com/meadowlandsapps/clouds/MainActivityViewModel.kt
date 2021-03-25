package com.meadowlandsapps.clouds

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.meadowlandsapps.clouds.db.WeatherDatabase
import com.meadowlandsapps.clouds.db.entity.Point
import com.meadowlandsapps.clouds.repository.ForecastRepository
import com.meadowlandsapps.clouds.ui.model.CurrentConditionsModel
import com.meadowlandsapps.clouds.ui.model.DailyForecastModel
import com.meadowlandsapps.clouds.ui.model.HourlyForecastModel
import kotlinx.coroutines.launch

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ForecastRepository

    init {
        val dao = WeatherDatabase.getDatabase(application).forecastDao()
        repository = ForecastRepository(dao)
    }

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    val currentConditions: LiveData<CurrentConditionsModel> = repository.currentConditions

    val daily: LiveData<List<DailyForecastModel>> = repository.dailyForecast

    val hourly: LiveData<List<HourlyForecastModel>> = repository.hourlyForecast

    fun loadForecast() {
        viewModelScope.launch {
            _isLoading.postValue(true)

            Log.d(MainActivityViewModel::class.simpleName, "Launching network fetch")
            val response = repository.fetchPoints(47.085476, -92.664109)
            val success = response.isSuccessful && response.body() != null
            if (success) {
                Log.d(MainActivityViewModel::class.simpleName, "Fetching Forecasts")
                val point = response.body() as Point
                repository.fetchForecast(point.properties.forecast.substringAfter(NOAAService.URL_BASE))
                repository.fetchHourlyForecast(
                    point.properties.forecastHourly.substringAfter(
                        NOAAService.URL_BASE
                    )
                )
            } else {
                // show error message
                Log.d(MainActivityViewModel::class.simpleName, "Error launching network fetch")
            }

            _isLoading.postValue(false)
        }
    }
}