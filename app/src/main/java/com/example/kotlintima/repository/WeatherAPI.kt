package com.example.kotlintima.repository

import com.example.kotlintima.repository.dto.WeatherDTO
import com.example.kotlintima.utlis.KEY_BUNDLE_LAT
import com.example.kotlintima.utlis.KEY_BUNDLE_LON
import com.example.kotlintima.utlis.YANDEX_API_KEY
import com.example.kotlintima.utlis.YANDEX_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherAPI {
    @GET(YANDEX_ENDPOINT)
    fun getWeather(
        @Header(YANDEX_API_KEY) apikey: String,
        @Query(KEY_BUNDLE_LAT) lat: Double,
        @Query(KEY_BUNDLE_LON) lon: Double
    ): Call<WeatherDTO>
}