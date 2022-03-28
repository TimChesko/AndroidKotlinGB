package com.example.kotlintima.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.lang.Thread.sleep


//задали параметры на старте (лежит Any, но вскоре будет ответ сервера про погоду)
//называется - свойство livedate
class MainViewModel(private val liveData: MutableLiveData<Any> = MutableLiveData()):ViewModel() {

    fun getData(): LiveData<Any>{
        return liveData
    }

    fun getWeather(){
        Thread{
            sleep(1000L)
            liveData.postValue(Any())
        }.start()
    }

}