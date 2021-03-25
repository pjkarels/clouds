package com.meadowlandsapps.clouds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.meadowlandsapps.clouds.R
import com.meadowlandsapps.clouds.ui.model.CurrentConditionsModel
import com.meadowlandsapps.clouds.ui.model.DailyForecastModel
import com.meadowlandsapps.clouds.ui.model.HourlyForecastModel

@Composable
fun CurrentCell(current: CurrentConditionsModel?) {
    Column {
        Sky(sky = current?.sky ?: stringResource(id = R.string.placeholder_value))
        Temp(temp = current?.temperature() ?: stringResource(id = R.string.placeholder_value))
        Wind(
            windSpeed = current?.windSpeed ?: stringResource(id = R.string.placeholder_value),
            windDirection = current?.windDirection
                ?: stringResource(id = R.string.placeholder_value)
        )
    }
}

@Composable
fun Hours(hourly: List<HourlyForecastModel>?) {
    if (hourly != null) {
        LazyColumn(modifier = Modifier.padding(16.dp)) {
            items(hourly) { hour ->
                HourlyCell(hour = hour)
            }
        }
    }
}

@Composable
fun HourlyCell(hour: HourlyForecastModel) {
    Column() {
        Sky(hour.sky ?: stringResource(id = R.string.placeholder_value))
        Wind(
            hour.windSpeed ?: stringResource(id = R.string.placeholder_value),
            hour.windDirection ?: stringResource(id = R.string.placeholder_value)
        )
    }
}

@Composable
fun Days(daily: List<DailyForecastModel>?) {
    if (daily != null) {
        LazyColumn {
            items(daily) { day ->
                DailyCell(day = day)
            }
        }
    }
}

@Composable
fun DailyCell(day: DailyForecastModel) {
    Column() {
        Sky(day.sky)
        Wind(
            day.windSpeed,
            day.windDirection
        )
    }
}

@Composable
fun Sky(sky: String) {
    Text(text = sky)
}

@Composable
fun Temp(temp: String) {
    Text(text = temp)
}

@Composable
fun DewPoint(temp: String) {
    Text(text = temp)
}

@Composable
fun High(temp: String) {
    Text(text = temp)
}

@Composable
fun Low(temp: String) {
    Text(text = temp)
}

@Composable
fun Wind(windSpeed: String, windDirection: String) {
    Column {
        Text(text = windSpeed)
        Text(text = windDirection)
    }
}

@Composable
fun Pressure(pressure: String) {
    Text(text = pressure)
}
