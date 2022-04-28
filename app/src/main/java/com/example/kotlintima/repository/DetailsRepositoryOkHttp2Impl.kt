package com.example.kotlintima.repository

import android.util.Log
import com.example.kotlintima.BuildConfig
import com.example.kotlintima.repository.dto.WeatherDTO
import com.example.kotlintima.utlis.YANDEX_API_KEY
import com.example.kotlintima.utlis.YANDEX_DOMAIN
import com.example.kotlintima.utlis.YANDEX_ENDPOINT
import com.example.kotlintima.utlis.convertDtoToModel
import com.example.kotlintima.view.details.DetailsFragment
import com.example.kotlintima.viewmodel.DetailsState
import com.example.kotlintima.viewmodel.DetailsViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

class DetailsRepositoryOkHttp2Impl : DetailsRepository {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val client = OkHttpClient()
        val builder = Request.Builder()
        builder.addHeader(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
        builder.url("$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=${city.lat}&lon=${city.lon}")
        val request = builder.build()
        val call = client.newCall(request)
        Thread {
            val response = call.execute()
            if (response.isSuccessful) {
                val serverResponse = response.body()!!.string()
                val weatherDTO: WeatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                val weather = convertDtoToModel(weatherDTO)
                weather.city = city
                callback.onResponse(weather)
                Log.d("@@@", "${response.isRedirect}")
            } else {
                Log.d("@@@", "${response.isRedirect}")
            }
        }.start()
    }
}