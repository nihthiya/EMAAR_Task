package com.example.emaartask.data.apiServices

import com.example.emaartask.data.model.response.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface UserListAPI{

    @GET("/api/?results=20")
    suspend fun getUserListAsync(@Query("page") pageCount: Int): Response<UserListResponse>
}