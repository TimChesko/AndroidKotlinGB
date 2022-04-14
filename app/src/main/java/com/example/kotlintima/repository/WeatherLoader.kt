package com.example.kotlintima.repository

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat:Double, lon:Double){
        val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
        val uri = URL(urlText)
        val urlConnection:HttpURLConnection =(uri.openConnection() as HttpURLConnection).apply {
            connectTimeout = 1000
            readTimeout = 1000
            addRequestProperty("X-Yandex-API-Key", "3c1afcf2-c7cd-4600-a403-e53a97917d98")
        }

        Thread{
            try {
                val headers = urlConnection.headerFields
                val responseCode = urlConnection.responseCode
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post {
                    onServerResponseListener.onResponse(weatherDTO)
                }
            } catch (e: Exception) {
                // TODO  HW "что-то пошло не так" Snackbar?
            }
            // TODO  HW disconnect() finally?
        }.start()
    }
}