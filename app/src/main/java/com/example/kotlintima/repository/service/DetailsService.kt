package com.example.kotlintima.repository.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.kotlintima.BuildConfig
import com.example.kotlintima.repository.dto.WeatherDTO
import com.example.kotlintima.utlis.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DetailsService(val name: String = "") : IntentService(name) {
    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@", "work DetailsService")
        intent?.let {
            val lon = it.getDoubleExtra(KEY_BUNDLE_LON, 0.0)
            val lat = it.getDoubleExtra(KEY_BUNDLE_LAT, 0.0)
            Log.d("@@@", "work DetailsService $lat $lon")

            val urlText = "$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=$lat&lon=$lon"
            val uri = URL(urlText)

            val urlConnection: HttpURLConnection =
                (uri.openConnection() as HttpURLConnection).apply {
                    addRequestProperty(
                        YANDEX_API_KEY,
                        BuildConfig.WEATHER_API_KEY
                    )
                }

            val codeUrl = urlConnection.responseCode
            when {
                codeUrl >= 500 -> {
                    Toast.makeText(this, "Error from server", Toast.LENGTH_LONG).show()
                }
                codeUrl >= 400 -> {
                    Toast.makeText(this, "Error from client", Toast.LENGTH_LONG).show()
                }
                codeUrl >= 200 -> {
                    val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                    val message = Intent(KEY_WAVE_SERVICE_BROADCAST)
                    message.putExtra(
                        KEY_BUNDLE_SERVICE_BROADCAST_WEATHER, weatherDTO
                    )
                    LocalBroadcastManager.getInstance(this).sendBroadcast(message)
                }
                else -> {
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}