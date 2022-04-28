package com.example.kotlintima.repository

import com.example.kotlintima.BuildConfig
import com.example.kotlintima.repository.dto.WeatherDTO
import com.example.kotlintima.utlis.MESSAGE_ERROR
import com.example.kotlintima.utlis.SERVER_ERROR
import com.example.kotlintima.utlis.YANDEX_DOMAIN
import com.example.kotlintima.utlis.convertDtoToModel
import com.example.kotlintima.viewmodel.DetailsViewModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRepositoryRetrofit2Impl : DetailsRepository {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val weatherAPI = Retrofit.Builder().apply {
            baseUrl(YANDEX_DOMAIN)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build().create(WeatherAPI::class.java)

        weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY, city.lat, city.lon).enqueue(object :
            Callback<WeatherDTO> {
            override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val weather = convertDtoToModel(it)
                        weather.city = city
                        callback.onResponse(weather)
                    }
                } else {
                    callback.onFail(Throwable(MESSAGE_ERROR))
                }
            }

            override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                callback.onFail(Throwable(SERVER_ERROR))
            }
        })
    }
}