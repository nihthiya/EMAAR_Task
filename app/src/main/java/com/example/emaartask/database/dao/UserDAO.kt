package com.example.emaartask.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.emaartask.database.model.UserModel

@Dao
interface UserDAO {
    @Query("select * from userTable")
    fun getUsers(): List<UserModel>

    @Query("select * from userTable where userUUID=:userId ")
    fun getUserByID(userId: String): UserModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(product: List<UserModel>)

    @Query("DELETE FROM userTable")
    fun deleteAll()
}