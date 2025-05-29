package com.example.weatherappdiana
import retrofit2.http.GET
import retrofit2.http.Query
interface WeatherApi {
    @GET("weather")
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") apiKey: String = "7a605d26619103656ca0d889f383bb11"
    ): WeatherData
}
