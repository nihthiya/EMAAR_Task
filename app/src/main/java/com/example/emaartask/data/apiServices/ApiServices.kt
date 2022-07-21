package com.example.emaartask.data.apiServices

import com.example.emaartask.data.model.Response.UserListResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface UserListAPI{

    @GET("/api/?results=20")
    suspend fun getUserListAsync(@Query("page") pageCount: Int): Response<UserListResponse>
}