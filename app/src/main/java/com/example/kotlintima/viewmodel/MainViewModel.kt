package com.example.kotlintima.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep


//задали параметры на старте (лежит Any, но вскоре будет ответ сервера про погоду)
//называется - свойство livedate
class MainViewModel(private val liveData: MutableLiveData<AppState> = MutableLiveData()):ViewModel() {

    fun getData(): LiveData<AppState>{
        return liveData
    }

    fun getWeather(){
        Thread{
            liveData.postValue(AppState.Loading)
            sleep(1000L)
            if((0..10).random()>5){
                liveData.postValue(AppState.Success(Any()))
            } else {
                liveData.postValue(AppState.Error(IllegalAccessException()))
            }
        }.start()
    }

}