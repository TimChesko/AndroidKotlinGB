package com.example.kotlintima.repository

import com.google.gson.annotations.SerializedName

data class CloudsDTO(
    @SerializedName("all")
    val all: Int
)