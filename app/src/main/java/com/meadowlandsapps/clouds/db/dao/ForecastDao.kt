package com.meadowlandsapps.clouds.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.meadowlandsapps.clouds.db.entity.Current
import com.meadowlandsapps.clouds.db.entity.Day
import com.meadowlandsapps.clouds.db.entity.Hour

@Dao
interface ForecastDao {
    @get:Query("SELECT * FROM current_table")
    val current: LiveData<Current>

    @get:Query("SELECT * FROM hour_table")
    val hourly: LiveData<List<Hour>>

    @get:Query("SELECT * FROM day_table")
    val daily: LiveData<List<Day>>

    @Query("SELECT * FROM current_table")
    suspend fun getCurrently(): Current

    @Query("SELECT * FROM hour_table")
    suspend fun getHourly(): List<Hour>

    @Query("SELECT * FROM day_table")
    suspend fun getDaily(): List<Day>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrently(current: Current)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourly(hours: List<Hour>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDaily(days: List<Day>)
}