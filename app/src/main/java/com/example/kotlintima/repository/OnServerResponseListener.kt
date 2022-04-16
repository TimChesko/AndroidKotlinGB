package com.example.kotlintima.repository

import com.example.kotlintima.viewmodel.ResponseState

fun interface OnServerResponseListener {
    fun onResponse(responseState: ResponseState)
}