package com.example.kotlintima.repository

fun interface OnServerResponse {
    fun onResponse(weatherDTO: WeatherDTO)
}