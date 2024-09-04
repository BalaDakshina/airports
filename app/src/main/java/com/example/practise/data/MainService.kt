package com.example.practise.data

import retrofit2.Response
import retrofit2.http.GET

interface MainService {
    @GET("/get/data")
    suspend fun getData(): Response<Any>
}