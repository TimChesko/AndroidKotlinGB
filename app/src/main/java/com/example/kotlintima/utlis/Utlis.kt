package com.example.kotlintima.utlis

import com.example.kotlintima.repository.Weather
import com.example.kotlintima.repository.dto.Fact
import com.example.kotlintima.repository.dto.WeatherDTO
import com.example.kotlintima.repository.getDefaultCity

//хранилище констант
const val KEY_BUNDLE_WEATHER = "weather"
const val YANDEX_DOMAIN = "https://api.weather.yandex.ru/"
const val YANDEX_ENDPOINT = "v2/informers?"
const val YANDEX_API_KEY = "X-Yandex-API-Key"
const val KEY_BUNDLE_LAT = "lat"
const val KEY_BUNDLE_LON = "lon"
const val KEY_BUNDLE_SERVICE_BROADCAST_WEATHER = "weather_s_b"
const val KEY_WAVE_SERVICE_BROADCAST = "myaction_way"
const val KEY_BUNDLE_SERVICE_MESSAGE = "service_message"
const val KEY_BUNDLE_ACTIVITY_MESSAGE = "activity_message"
const val KEY_WAVE = "myaction"
const val SERVER_ERROR = "The server is not responding"
const val NO_DATA = "Connection error"
const val MESSAGE_ERROR = "Something went wrong"
//I am writing in English because it is too difficult to get used to switching to linux language XD

fun convertDtoToModel(weatherDTO: WeatherDTO): Weather {
    val fact: Fact = weatherDTO.fact
    return (Weather(getDefaultCity(), fact.temp, fact.feelsLike, fact.icon))
}