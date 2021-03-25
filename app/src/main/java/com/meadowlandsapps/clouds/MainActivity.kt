/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meadowlandsapps.clouds

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.meadowlandsapps.clouds.ui.CurrentCell
import com.meadowlandsapps.clouds.ui.Days
import com.meadowlandsapps.clouds.ui.Hours
import com.meadowlandsapps.clouds.ui.theme.MyTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm by viewModels<MainActivityViewModel>()
        vm.loadForecast()
        setContent {
            MyTheme {
                MyApp(vm)
            }
        }
    }
}

@Composable
fun MainScreen(vm: MainActivityViewModel) {
    val current by vm.currentConditions.observeAsState()

    CurrentCell(current)

    val hours by vm.hourly.observeAsState()
    Hours(hours)

    val days by vm.daily.observeAsState()
    Days(days)
}

@Composable
fun MyApp(vm: MainActivityViewModel) {
    Surface(color = MaterialTheme.colors.background) {
        MainScreen(vm = vm)
    }
}

//@Preview("Light Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun LightPreview() {
//    MyTheme {
//        MyApp()
//    }
//}
//
//@Preview("Dark Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun DarkPreview() {
//    MyTheme(darkTheme = true) {
//        MyApp()
//    }
//}
