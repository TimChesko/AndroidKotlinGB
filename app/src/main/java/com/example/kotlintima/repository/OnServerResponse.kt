package com.example.kotlintima.repository

import com.example.kotlintima.repository.dto.WeatherDTO

fun interface OnServerResponse {
    fun onResponse(weatherDTO: WeatherDTO)
}