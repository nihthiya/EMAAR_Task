package com.example.emaartask.data.repository

import com.example.emaartask.data.network.UserListApiHelper
import com.example.emaartask.database.dao.UserDAO
import com.example.emaartask.database.model.UserModel
import javax.inject.Inject


class UserListRepository @Inject constructor(private val apiHelper: UserListApiHelper, private val userDAO: UserDAO) {

    suspend fun getUserList(pageCount: Int) = apiHelper.getUserListAsync(pageCount)

    suspend fun insertUser(user: List<UserModel>) = userDAO.addUser(user)

    suspend fun getUserFromDB() = userDAO.getUsers()

    suspend fun getUserByID(userID: String) = userDAO.getUserByID(userID)

    suspend fun deleteAll() = userDAO.deleteAll()


}
