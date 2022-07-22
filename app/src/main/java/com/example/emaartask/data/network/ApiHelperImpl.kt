package com.example.emaartask.data.network

import com.example.emaartask.data.apiServices.UserListAPI
import com.example.emaartask.data.model.response.UserListResponse
import retrofit2.Response
import javax.inject.Inject


class UserListApiHelperImpl @Inject constructor(private val productListApi: UserListAPI) :
    UserListApiHelper {

    override suspend fun getUserListAsync(pageCount: Int): Response<UserListResponse> =
        productListApi.getUserListAsync(pageCount)

}


