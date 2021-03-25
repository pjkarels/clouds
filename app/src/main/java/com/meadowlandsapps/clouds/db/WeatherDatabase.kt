package com.meadowlandsapps.clouds.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.meadowlandsapps.clouds.db.dao.ForecastDao
import com.meadowlandsapps.clouds.db.entity.Current
import com.meadowlandsapps.clouds.db.entity.Day
import com.meadowlandsapps.clouds.db.entity.Hour

@Database(entities = [Current::class, Hour::class, Day::class], version = 1)
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