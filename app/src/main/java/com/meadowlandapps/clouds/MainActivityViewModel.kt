package com.meadowlandapps.clouds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.meadowlandapps.clouds.db.Forecast
import com.meadowlandapps.clouds.db.WeatherDatabase
import com.meadowlandapps.clouds.repository.ForecastRepository
import com.meadowlandapps.clouds.ui.model.CurrentConditionsModel
import com.meadowlandapps.clouds.ui.model.DailyForecastModel
import com.meadowlandapps.clouds.ui.model.HourlyForecastModel
import kotlinx.coroutines.launch
import java.io.InputStreamReader

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ForecastRepository

    init {
        val dao = WeatherDatabase.getDatabase(application).forecastDao()
        repository = ForecastRepository(dao)

        // seed mock data
        val inputStreamReader =
            InputStreamReader(getApplication<Application>().resources.openRawResource(R.raw.forecast))
        val json = inputStreamReader.readText()
        val forecast = Gson().fromJson(json, Forecast::class.java)
        viewModelScope.launch {
            // insert data into database
            dao.insertCurrently(forecast.currently)
            dao.insertHourly(forecast.hourly.data)
            dao.insertDaily(forecast.daily.data)
        }
    }

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _currentConditions = MutableLiveData<CurrentConditionsModel>()
    val currentConditions: LiveData<CurrentConditionsModel> = _currentConditions

    private val _daily = MutableLiveData<DailyForecastModel>()
    val daily: LiveData<DailyForecastModel> get() = _daily

    private val _hourly = MutableLiveData<HourlyForecastModel>()
    val hourly: LiveData<HourlyForecastModel> = _hourly

    fun loadForecast() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            _currentConditions.value = repository.getCurrentConditions()
            _isLoading.postValue(false)
        }
    }
}