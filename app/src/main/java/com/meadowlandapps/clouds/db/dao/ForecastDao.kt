package com.meadowlandapps.clouds.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.meadowlandapps.clouds.db.Currently
import com.meadowlandapps.clouds.db.Day
import com.meadowlandapps.clouds.db.Hour

@Dao
interface ForecastDao {
    @get:Query("SELECT * FROM currently_table")
    val currently: LiveData<Currently>

    @get:Query("SELECT * FROM hour_table")
    val hourly: LiveData<List<Hour>>

    @get:Query("SELECT * FROM daily_table")
    val daily: LiveData<List<Day>>

    @Query("SELECT * FROM currently_table")
    suspend fun getCurrently(): Currently

    @Query("SELECT * FROM hour_table")
    suspend fun getHourly(): List<Hour>

    @Query("SELECT * FROM daily_table")
    suspend fun getDaily(): List<Day>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrently(currently: Currently)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHourly(hours: List<Hour>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDaily(days: List<Day>)
}