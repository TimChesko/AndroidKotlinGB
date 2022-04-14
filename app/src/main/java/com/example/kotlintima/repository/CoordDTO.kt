package com.example.kotlintima.repository

import com.google.gson.annotations.SerializedName

data class CoordDTO(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)