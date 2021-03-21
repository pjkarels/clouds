package com.meadowlandapps.clouds.db

class Forecast(
    val currently: Currently,
    val hourly: Hourly,
    val daily: Daily
) {
}