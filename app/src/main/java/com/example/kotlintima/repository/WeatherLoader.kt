package com.example.kotlintima.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kotlintima.repository.dto.WeatherDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat: Double, lon: Double){
        val urlText = "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=85a237a3fa1b8c35abfb5d0bacdf3137"
        val uri = URL(urlText)
        val urlConnection:HttpURLConnection =(uri.openConnection() as HttpURLConnection)

        Thread{
            try {
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post {
                    onServerResponseListener.onResponse(weatherDTO)
                }
            } catch (e: Exception) {
                Log.d("@@@", "Error $e")
            }
            // TODO  HW disconnect() finally?
        }.start()
    }
}