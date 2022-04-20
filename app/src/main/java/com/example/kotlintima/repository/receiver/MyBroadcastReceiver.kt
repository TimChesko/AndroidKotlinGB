package com.example.kotlintima.repository.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.kotlintima.utlis.KEY_BUNDLE_SERVICE_MESSAGE

class MyBroadcastReceiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let {
            val extra = it.getStringExtra(KEY_BUNDLE_SERVICE_MESSAGE)
            Log.d("@@@","MyBroadcastReceiver // onReceive -> ${intent!!.action}")
        }
    }
}