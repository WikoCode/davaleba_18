package com.example.davaleba_18.api

import com.example.davaleba_18.data.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(@Query("page") page: Int): Response<ApiResponse>
}