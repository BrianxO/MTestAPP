package com.example.mtestapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import java.text.SimpleDateFormat
import java.util.Locale



data class Forcast(val day: String, val forecastTemp: ForecastTemp, val sunrise: String, val sunset: String)

data class ForecastTemp(val minTemp: Int, val maxTemp: Int)


@Composable
fun ForecastScreen(zipCode: String) {
    val frcViewModel: FrcViewModel = hiltViewModel()
    val forecastState by frcViewModel.forecastState.observeAsState()
    val safeForecastState = forecastState ?: FrcViewModel.ForecastState.Loading
    val cnt = 5

    LaunchedEffect(frcViewModel) {
        frcViewModel.getForecastData(zipCode, cnt)
    }

    Column {
        Box(modifier = Modifier.fillMaxWidth().height(56.dp).background(Color.Green)) {
            Text("Forecast", modifier = Modifier.align(Alignment.CenterStart).padding(start = 8.dp), fontSize = 24.sp)
        }

        when (safeForecastState) {
            is FrcViewModel.ForecastState.Loading -> {
                Text(text = "Loading...", fontSize = 20.sp, textAlign = TextAlign.Center)
            }

            is FrcViewModel.ForecastState.Success -> {
                val forecastData = (safeForecastState as FrcViewModel.ForecastState.Success).forecastData
                val forecastItems = forecastData.forecasts

                LazyColumn {
                    items(forecastItems) { forecastItem ->
                        ForecastRow(forecastItem)
                    }
                }
            }

            is FrcViewModel.ForecastState.Error -> {
                val error = (safeForecastState).error
                Text(text = "Error: $error", fontSize = 20.sp, color = Color.Red)
            }
        }
    }
}

@Composable
fun ForecastRow(forecast: ForecastItem) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Image(
            painter = rememberImagePainter(forecast.iconUrl),
            contentDescription = stringResource(id = R.string.weather_icon),
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = SimpleDateFormat("MMM dd", Locale.getDefault()).format(forecast.date),
            fontSize = 20.sp
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "High: ${forecast.temp?.max}°", fontSize = 16.sp)
            Text(text = "Low: ${forecast.temp?.min}°", fontSize = 16.sp)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Sunrise: ${SimpleDateFormat("hh:mm a", Locale.getDefault()).format(forecast.sunriseTime)}",
                fontSize = 16.sp
            )
            Text(
                text = "Sunset: ${SimpleDateFormat("hh:mm a", Locale.getDefault()).format(forecast.sunsetTime)}",
                fontSize = 16.sp
            )
        }
    }
}


