package com.example.emaartask.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "userUUID")
    var userUUID: String = "",

    @ColumnInfo(name = "Name")
    var name: String = "",

    @ColumnInfo(name = "Email")
    var email: String = "",

    @ColumnInfo(name = "Country")
    var country: String = "",

    @ColumnInfo(name = "Date")
    var date: String = "",

    @ColumnInfo(name = "Avatar")
    var avatar: String = "",

    @ColumnInfo(name = "AvatarLarge")
    var avatarLarge: String = "",

    @ColumnInfo(name = "Age")
    var age: Int = 0,

    @ColumnInfo(name = "DOB")
    var dob: String = "",

    @ColumnInfo(name = "City")
    var city: String = "",

    @ColumnInfo(name = "State")
    var state: String = "",

    @ColumnInfo(name = "Postcode")
    var postcode: String = "",
    ) {
    constructor():this(0,"","","","","","","",0,"","","","")
}