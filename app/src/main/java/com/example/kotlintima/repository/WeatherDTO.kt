package com.example.kotlintima.repository

import com.google.gson.annotations.SerializedName

data class WeatherDTO(
    @SerializedName("base")
    val base: String,
    @SerializedName("cloudsDTO")
    val cloudsDTO: CloudsDTO,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coordDTO")
    val coordDTO: CoordDTO,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("mainDTO")
    val mainDTO: MainDTO,
    @SerializedName("name")
    val name: String,
    @SerializedName("sysDTO")
    val sysDTO: SysDTO,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherXDTO>,
    @SerializedName("windDTO")
    val windDTO: WindDTO
)