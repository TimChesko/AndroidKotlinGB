package com.example.kotlintima.repository

import com.google.gson.annotations.SerializedName

data class WindDTO(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("speed")
    val speed: Double
)