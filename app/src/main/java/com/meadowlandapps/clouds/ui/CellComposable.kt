package com.meadowlandapps.clouds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.meadowlandapps.clouds.MainActivityViewModel
import com.meadowlandapps.clouds.R
import com.meadowlandapps.clouds.ui.model.DailyForecastModel
import com.meadowlandapps.clouds.ui.model.HourlyForecastModel

@Composable
fun CurrentCell(vm: MainActivityViewModel) {
    val current by vm.currentConditions.observeAsState()
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
fun HourlyCell(hourly: List<HourlyForecastModel>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(hourly) { item ->
            Column() {
                Sky(item.sky ?: stringResource(id = R.string.placeholder_value))
                Wind(
                    item.windSpeed ?: stringResource(id = R.string.placeholder_value),
                    item.windDirection ?: stringResource(id = R.string.placeholder_value)
                )
            }
        }
    }
}

@Composable
fun DailyCell(daily: List<DailyForecastModel>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(daily) { item ->
            Column() {
                Sky(item.sky ?: stringResource(id = R.string.placeholder_value))
                Wind(
                    item.windSpeed ?: stringResource(id = R.string.placeholder_value),
                    item.windDirection ?: stringResource(id = R.string.placeholder_value)
                )
            }
        }
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
