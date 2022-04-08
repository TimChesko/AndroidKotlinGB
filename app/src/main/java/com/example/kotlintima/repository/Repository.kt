package com.example.kotlintima.repository

interface Repository {
    fun getWeatherFromServer():Weather //вернуть погоду из сервера
    fun getWeatherFromLocalStorage():Weather //вернуть погоду локально
}