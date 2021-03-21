package com.meadowlandapps.clouds.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.meadowlandapps.clouds.db.dao.ForecastDao

@Database(entities = [Currently::class, Hourly::class, Daily::class], version = 1)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getDatabase(context: Context): WeatherDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    "weather_database"
                )
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}