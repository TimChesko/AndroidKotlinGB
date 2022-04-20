package com.example.kotlintima.repository.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.kotlintima.utlis.KEY_BUNDLE_ACTIVITY_MESSAGE
import com.example.kotlintima.utlis.KEY_BUNDLE_SERVICE_MESSAGE
import com.example.kotlintima.utlis.KEY_WAVE
import java.lang.Thread.sleep

/**
 * TODO почему IntentService deprecated ?
 * Чтобы улучшить пользовательский опыт, Android 8.0 (уровень API 26) накладывает ограничения на то,
 * что приложения могут во время работы что-то делать в фоновом режиме.
 * Ну а тк у нас фоновая работа в Android нарушена, IntentService сделали deprecated.
 * Можно заменить: WorkManager или JobIntentService.
 * Документация: https://developer.android.com/reference/android/app/IntentService
 **/

class MainService(val name: String = "") : IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@", "work MainService")
        intent?.let {
            val extra = it.getStringExtra(KEY_BUNDLE_ACTIVITY_MESSAGE)
            Log.d("@@@", "work MainService $extra")
            sleep(1000L)
            val message = Intent(KEY_WAVE)
            message.putExtra(
                KEY_BUNDLE_SERVICE_MESSAGE,
                "привет активити и тебе всего хорошего"
            )
            sendBroadcast(message)
        }
    }
}