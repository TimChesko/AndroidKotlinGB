package com.example.kotlintima.view.weatherlist

import androidx.recyclerview.widget.DiffUtil
import com.example.kotlintima.repository.Weather


class WeatherDiffUtilCallback(
    private val oldList: List<Weather>,
    private val newList: List<Weather>
) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldWeather: Weather = oldList[oldItemPosition]
        val newWeather: Weather = newList[newItemPosition]
        return oldWeather.city === newWeather.city
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldWeather: Weather = oldList[oldItemPosition]
        val newWeather: Weather = newList[newItemPosition]
        return (oldWeather.feelsLike == newWeather.feelsLike
                && oldWeather.temperature == newWeather.temperature)
    }

}