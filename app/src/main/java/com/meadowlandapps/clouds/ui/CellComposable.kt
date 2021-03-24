package com.meadowlandapps.clouds.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import com.meadowlandapps.clouds.MainActivityViewModel
import com.meadowlandapps.clouds.R

@Composable
fun CurrentCell(vm: MainActivityViewModel) {
    val current by vm.currentConditions.observeAsState()
    Column {
        Sky(sky = current?.sky ?: stringResource(id = R.string.placeholder_value))
        Temp(temp = current?.temp ?: stringResource(id = R.string.placeholder_value))
        DewPoint(temp = current?.dewPoint ?: stringResource(id = R.string.placeholder_value))
        Wind(
            windSpeed = current?.windSpeed ?: stringResource(id = R.string.placeholder_value),
            windDirection = current?.windDirection
                ?: stringResource(id = R.string.placeholder_value)
        )
        Pressure(pressure = current?.pressure ?: stringResource(id = R.string.placeholder_value))
    }
}

@Composable
fun HourlyCell(vm: MainActivityViewModel) {
    val hourly by vm.hourly.observeAsState()
    Column {
        Sky(sky = hourly?.sky ?: stringResource(id = R.string.placeholder_value))
        Temp(temp = hourly?.temp ?: stringResource(id = R.string.placeholder_value))
        DewPoint(temp = hourly?.dewPoint ?: stringResource(id = R.string.placeholder_value))
        Wind(
            windSpeed = hourly?.windSpeed ?: stringResource(id = R.string.placeholder_value),
            windDirection = hourly?.windDirection ?: stringResource(id = R.string.placeholder_value)
        )
        Pressure(pressure = hourly?.pressure ?: stringResource(id = R.string.placeholder_value))
    }
}

@Composable
fun DailyCell(vm: MainActivityViewModel) {
    val daily by vm.daily.observeAsState()
    Column {
        Sky(daily?.sky ?: stringResource(id = R.string.placeholder_value))
        High(daily?.highTemp ?: stringResource(id = R.string.placeholder_value))
        Low(daily?.lowTemp ?: stringResource(id = R.string.placeholder_value))
        DewPoint(daily?.dewPoint ?: stringResource(id = R.string.placeholder_value))
        Wind(
            daily?.windSpeed ?: stringResource(id = R.string.placeholder_value),
            daily?.windDirection ?: stringResource(id = R.string.placeholder_value)
        )
        Pressure(daily?.pressure ?: stringResource(id = R.string.placeholder_value))
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
