package com.meadowlandapps.clouds

import com.meadowlandapps.clouds.db.entity.Forecast
import com.meadowlandapps.clouds.db.entity.Point
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NOAAService {

    companion object {
        const val URL_BASE = "https://api.weather.gov/"
    }

    @GET("{endpoint}")
    fun getPoints(@Path("endpoint") endpoint: String): Call<Point>

    @GET("{endpoint}")
    fun getForecast(@Path("endpoint") endpoint: String): Call<Forecast>
}