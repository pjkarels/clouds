package com.meadowlandapps.clouds.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currently_table")
data class Currently(
    @PrimaryKey val time: Long,
    val summary: String,
    val temperature: Double,
    val apparentTemperature: Double,
    val dewPoint: Double,
    val humidity: Double,
    val pressure: Double,
    val windSpeed: Double,
    val windBearing: Int,
    val cloudCover: Double
) {
}