package com.meadowlandapps.clouds

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.meadowlandapps.clouds.db.Forecast
import com.meadowlandapps.clouds.db.WeatherDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.InputStreamReader

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    init {
        // seed mock data
        val inputStreamReader =
            InputStreamReader(getApplication<Application>().resources.openRawResource(R.raw.forecast))
        val json = inputStreamReader.readText()
        val forecast = Gson().fromJson(json, Forecast::class.java)
        viewModelScope.launch(Dispatchers.IO) {

            // insert data into database
            val dao = WeatherDatabase.getDatabase(application).forecastDao()
            dao.insertCurrently(forecast.currently)
        }
    }
}