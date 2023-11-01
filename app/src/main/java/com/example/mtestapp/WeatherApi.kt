package com.example.mtestapp


import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {



    @GET("https://api.openweathermap.org/data/2.5/weather")
    suspend fun getCurrentData(
        @Query("zip") zip: String,
        @Query("units") units: String = "imperial",
        @Query("appid") apiKey: String = Constants.API_KEY
    ): CurrentWeatherData

    @GET("https://api.openweathermap.org/data/2.5/forecast/daily")
    suspend fun getForecastData(
        @Query("zip") zip: String,
        @Query("cnt") cnt: Int,
        @Query("units") units: String = "imperial",
        @Query("appid") apiKey: String = Constants.API_KEY
    ): WeatherData
}

