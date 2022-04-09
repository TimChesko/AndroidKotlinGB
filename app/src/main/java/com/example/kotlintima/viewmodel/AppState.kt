package com.example.kotlintima.viewmodel

import com.example.kotlintima.repository.Weather

sealed class AppState {
    object Loading:AppState()
    data class Success(val weatherList:List<Weather>):AppState()
    data class Error(val error:Throwable):AppState()
}
