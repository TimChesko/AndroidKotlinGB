package com.example.kotlintima.repository

import com.example.kotlintima.viewmodel.DetailsViewModel

interface DetailsRepository {
    fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback)
}