package com.example.kotlintima.repository

class RepositoryImpl:Repository {

    override fun getWeatherFromServer():Weather {
        return Weather()
    }

    override fun getWorldWeatherFromLocalStorage():List<Weather> {
        return getWorldCities()
    }

    override fun getRussianWeatherFromLocalStorage():List<Weather> {
        return getRussianCities()
    }
}