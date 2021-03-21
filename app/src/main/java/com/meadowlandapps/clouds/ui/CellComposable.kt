package com.meadowlandapps.clouds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.meadowlandapps.clouds.ui.model.DailyForecastModel

@Composable
fun DailyCell(dailyModel: DailyForecastModel) {
    Column {
        High()
        Sky()
        Low()
        DewPoint()
        Wind()
        Pressure()
    }

}

@Composable
fun Sky() {

}

@Composable
fun High() {

}

@Composable
fun Low() {

}

@Composable
fun Wind() {

}

@Composable
fun Pressure() {

}

@Composable
fun DewPoint() {

}
