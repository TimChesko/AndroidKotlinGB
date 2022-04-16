package com.example.kotlintima.repository

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.kotlintima.BuildConfig
import com.example.kotlintima.repository.dto.WeatherDTO
import com.example.kotlintima.view.details.DetailsFragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_details.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherLoader(private val onServerResponseListener: OnServerResponse,
                    private val onResponseListener: OnServerResponseListener) {

    fun loadWeather(lat: Double, lon: Double){
//        val urlText = "https://api.openweathermap.org/data/2.5/weather?lat=$lat&lon=$lon&appid=85a237a3fa1b8c35abfb5d0bacdf3137&units=metric"
        //с сайта open weather - если вам нужно, можете мой api использовать
        val urlText = "http://212.86.114.27/v2/informers?lat=$lat&lon=$lon"
        val uri = URL(urlText)
        val urlConnection:HttpURLConnection =(uri.openConnection() as HttpURLConnection).apply {
            addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)
        }

        val serverSide = 500
        val clientSide = 400
        val responseOk = 200..clientSide

        Thread{
            try {
                val responseCode = urlConnection.responseCode
                if (responseCode >= serverSide){
                    with(DetailsFragment()) {mainView.showSnackBar(mainView, "Ошибка со стороны сервера")}
                    Log.d("@@@", "Error $responseCode")
                } else if (responseCode >= clientSide){
                    with(DetailsFragment()) {mainView.showSnackBar(mainView, "Ошибка со стороны клиента")}
                    Log.d("@@@", "Error $responseCode")
                } else if (responseCode in responseOk){
                    val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                    Handler(Looper.getMainLooper()).post {
                        onServerResponseListener.onResponse(weatherDTO)
                    }
                }
            }
            catch (e:NullPointerException){
                Log.d("@@@", "Пришел Null")
            }
            finally {
                urlConnection.disconnect()
            }
        }.start()
    }
}