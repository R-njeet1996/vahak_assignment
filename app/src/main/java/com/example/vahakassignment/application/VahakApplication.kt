package com.example.vahakassignment.application

import android.app.Application
import com.example.vahakassignment.api.ApiClient

class VahakApplication :Application()
{
    override fun onCreate() {
        super.onCreate()
        ApiClient.init()
    }
}