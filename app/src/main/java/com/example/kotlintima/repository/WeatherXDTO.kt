package com.example.kotlintima.repository

import com.google.gson.annotations.SerializedName

data class WeatherXDTO(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)