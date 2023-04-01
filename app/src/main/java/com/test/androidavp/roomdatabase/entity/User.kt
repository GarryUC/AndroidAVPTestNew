package com.test.androidavp.roomdatabase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String? = "",
    @ColumnInfo(name = "email") val email: String = "",
    @ColumnInfo(name = "password") val password: String = "",
)