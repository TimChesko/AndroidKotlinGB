package com.example.kotlintima.viewmodel

import com.example.kotlintima.repository.dto.WeatherDTO

sealed class DetailsState {
    object Loading:DetailsState()
    data class Success(val weather: WeatherDTO):DetailsState()
    data class Error(val error:Throwable):DetailsState()
}