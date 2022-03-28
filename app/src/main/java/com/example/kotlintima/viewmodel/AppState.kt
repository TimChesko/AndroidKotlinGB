package com.example.kotlintima.viewmodel


//sealed - запечатанный класс\ разные сигнатуры (any, int, throwable)
sealed class AppState {
    object Loading:AppState()
    data class Success(val weatherData: Any):AppState()
    data class Error(val error: Throwable):AppState()
}