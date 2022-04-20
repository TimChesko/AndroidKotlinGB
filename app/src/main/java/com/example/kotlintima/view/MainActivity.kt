package com.example.kotlintima.view

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlintima.R
import com.example.kotlintima.repository.receiver.MyBroadcastReceiver
import com.example.kotlintima.repository.service.MainService
import com.example.kotlintima.utlis.KEY_BUNDLE_ACTIVITY_MESSAGE
import com.example.kotlintima.view.threads.ThreadsFragment
import com.example.kotlintima.view.weatherlist.WeatherListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, WeatherListFragment.newInstance()).commit()
        }
        startService(Intent(this, MainService::class.java).apply {
            putExtra(KEY_BUNDLE_ACTIVITY_MESSAGE, "- сервис")
        })

        val receiver = MyBroadcastReceiver()
        registerReceiver(receiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))
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
                if (!thread) {
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


