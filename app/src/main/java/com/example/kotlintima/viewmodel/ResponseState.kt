package com.example.kotlintima.viewmodel

sealed class ResponseState {
    object Response100:ResponseState()
    object Response200:ResponseState()
    object Response300:ResponseState()
    object Response400:ResponseState()
    object Response500:ResponseState()
}
