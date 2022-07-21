package com.example.emaartask.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.emaartask.database.dao.UserDAO
import com.example.emaartask.database.model.UserModel

@Database(entities = [UserModel::class], version = 2, exportSchema = false)
abstract class Database : RoomDatabase() {
    abstract val userDAO: UserDAO
}