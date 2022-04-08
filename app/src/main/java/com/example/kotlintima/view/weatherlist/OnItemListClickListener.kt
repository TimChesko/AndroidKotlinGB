package com.example.kotlintima.view.weatherlist

import com.example.kotlintima.repository.Weather

interface OnItemListClickListener {
    fun onItemClick(weather: Weather)
}