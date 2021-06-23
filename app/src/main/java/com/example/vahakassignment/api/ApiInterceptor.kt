package com.example.vahakassignment.api


import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor:Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val originalRequest = requestBuilder.build()
        return chain.proceed(originalRequest)
    }
}