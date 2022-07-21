package com.example.emaartask.data.network

import com.example.emaartask.data.model.Response.UserListResponse
import retrofit2.Response

interface UserListApiHelper {

    suspend fun getUserListAsync(pageCount: Int): Response<UserListResponse>

}