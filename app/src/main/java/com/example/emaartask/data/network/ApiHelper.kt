package com.example.emaartask.data.network

import com.example.emaartask.data.model.response.UserListResponse
import retrofit2.Response

interface UserListApiHelper {

    suspend fun getUserListAsync(pageCount: Int): Response<UserListResponse>

}