package com.example.emaartask.data.repository

import com.example.emaartask.data.network.UserListApiHelper
import com.example.emaartask.database.dao.UserDAO
import com.example.emaartask.database.model.UserModel
import javax.inject.Inject


class UserListRepository @Inject constructor(private val apiHelper: UserListApiHelper, private val userDAO: UserDAO) {

    suspend fun getUserList(pageCount: Int) = apiHelper.getUserListAsync(pageCount)

    fun insertUser(user: List<UserModel>) = userDAO.addUser(user)

    fun getUserFromDB() = userDAO.getUsers()

    fun getUserByID(userID: String) = userDAO.getUserByID(userID)

    fun deleteAll() = userDAO.deleteAll()


}
