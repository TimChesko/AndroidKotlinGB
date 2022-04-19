package com.example.kotlintima.repository.service

import android.app.IntentService
import android.content.Intent
import android.util.Log

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
    }
}