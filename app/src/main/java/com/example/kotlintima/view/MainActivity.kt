package com.example.kotlintima.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintima.R
import com.example.kotlintima.view.weatherlist.WeatherListFragment

abstract class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
    }
}


