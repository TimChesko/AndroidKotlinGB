package com.example.kotlintima.repository

import android.util.Log

class RepositoryImpl:Repository {
    override fun getWeatherFromServer():Weather {
        Thread.sleep(2000L) // эмуляция сетевого запроса
        Log.d("myLogs", "From server")
        return Weather()// эмуляция ответа
    }

    override fun getWeatherFromLocalStorage():Weather {
        Thread.sleep(20L) // эмуляция запроса локального
        Log.d("myLogs", "From local storage")
        return Weather()// эмуляция ответа
    }
}