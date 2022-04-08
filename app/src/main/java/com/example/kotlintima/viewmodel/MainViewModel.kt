package com.example.kotlintima.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlintima.repository.RepositoryImpl
import java.lang.Thread.sleep


class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: RepositoryImpl = RepositoryImpl()

) :
    ViewModel() {
    fun getData(): LiveData<AppState> {
        return liveData
    }

    fun getWeather() {
        Thread {
            liveData.postValue(AppState.Loading)
            sleep(1000L)
            if ((0..10).random() > 5) {
                val answer =
                    if (checkConnect()) repository.getWeatherFromServer() else repository.getWeatherFromLocalStorage()
                liveData.postValue(AppState.Success(answer))
            } else {
                liveData.postValue(AppState.Error(IllegalAccessException()))
            }
        }.start()
    }

    private fun checkConnect(): Boolean {
        return (0..2).random() <= 1
        //сделать проверку подключения к интернету
    }

}