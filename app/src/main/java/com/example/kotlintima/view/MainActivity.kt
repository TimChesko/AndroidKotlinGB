package com.example.kotlintima.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintima.R
import com.example.kotlintima.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private var thread = true
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        thread = !thread
        when (item.itemId) {
            R.id.action_threads -> {
                if (thread){
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, ThreadsFragment.newInstance()).commit()
                } else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.container, WeatherListFragment.newInstance()).commit()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


