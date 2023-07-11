package com.example.mtestapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


data class DayForecast(val day: String, val forecastTemp: ForecastTemp, val sunrise: String, val sunset: String)

data class ForecastTemp(val minTemp: Int, val maxTemp: Int)


@Composable
fun ForecastScreen() {
    val forecastItems = listOf(
        DayForecast("April 1", ForecastTemp(20, 25), "6:00 AM", "6:00 PM"),
        DayForecast("April 2", ForecastTemp(18, 24), "5:45 AM", "6:15 PM"),
        DayForecast("April 3", ForecastTemp(22, 27), "6:10 AM", "6:30 PM"),
        DayForecast("April 4", ForecastTemp(19, 23), "6:05 AM", "6:20 PM"),
        DayForecast("April 5", ForecastTemp(21, 26), "5:55 AM", "6:25 PM"),
        DayForecast("April 6", ForecastTemp(17, 22), "6:15 AM", "6:35 PM"),
        DayForecast("April 7", ForecastTemp(23, 28), "6:20 AM", "6:40 PM"),
        DayForecast("April 8", ForecastTemp(20, 25), "6:30 AM", "6:50 PM"),
        DayForecast("April 9", ForecastTemp(18, 24), "6:25 AM", "6:45 PM"),
        DayForecast("April 10", ForecastTemp(22, 27), "6:40 AM", "7:00 PM"),
        DayForecast("April 11", ForecastTemp(19, 23), "6:35 AM", "6:55 PM"),
        DayForecast("April 12", ForecastTemp(21, 26), "6:50 AM", "7:10 PM"),
        DayForecast("April 13", ForecastTemp(17, 22), "6:55 AM", "7:15 PM"),
        DayForecast("April 14", ForecastTemp(23, 28), "7:05 AM", "7:25 PM"),
        DayForecast("April 15", ForecastTemp(20, 25), "7:10 AM", "7:30 PM"),
        DayForecast("April 16", ForecastTemp(18, 24), "7:20 AM", "7:40 PM"),
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            ForecastScreenTitle()
        }
        items(forecastItems) { forecast ->
            ForecastRow(forecast)
        }
    }
}

@Composable
fun ForecastRow(dayForecast: DayForecast) {
    Row(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.sun),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row(modifier = Modifier.padding(16.dp)) {
                Text(text = "${dayForecast.day}")
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Temp: ${dayForecast.forecastTemp.maxTemp}°F",
                    textAlign = TextAlign.End
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "Sunrise: ${dayForecast.sunrise}", modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(
                    text = "High: ${dayForecast.forecastTemp.maxTemp}°F",
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Low: ${dayForecast.forecastTemp.minTemp}°F",
                    modifier = Modifier.weight(1f)
                )
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Text(text = "Sunset: ${dayForecast.sunset}", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ForecastScreenTitle() {
    val titleColor = Color.Red

    Surface(modifier = Modifier.fillMaxWidth(), color = titleColor) {
        Text(
            text = stringResource(R.string.forecast_screen_title),
            modifier = Modifier.padding(16.dp),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewForecastScreen() {
    ForecastScreen()
}