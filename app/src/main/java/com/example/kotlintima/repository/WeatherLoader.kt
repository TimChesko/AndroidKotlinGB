package com.example.kotlintima.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kotlintima.BuildConfig
import com.example.kotlintima.repository.dto.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherLoader(
    private val onServerResponse: OnServerResponse,
    private val oServerResponseListener: OnServerResponseListener
) {

    fun loadWeather(lat: Double, lon: Double) {
        val urlText = "http://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
        val uri = URL(urlText)
        val urlConnection: HttpURLConnection = (uri.openConnection() as HttpURLConnection).apply {
            addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)
        }

        Thread {
            try {
                val responseCode = urlConnection.responseCode
                val messageCode = urlConnection.responseMessage
                Log.d("@@@", "Код: $responseCode --> Сообщение: $messageCode")
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post {
                    onServerResponse.onResponse(weatherDTO)
                }
            } catch (e: NullPointerException) {
                Log.d("@@@", "Пришел Null")
            } finally {
                urlConnection.disconnect()
            }
        }.start()
    }
}