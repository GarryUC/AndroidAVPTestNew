package com.test.androidavp.roomdatabase.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.it.task.roomdatabase.ArrayListConverter
import com.test.androidavp.roomdatabase.dao.RocketDao
import com.test.androidavp.roomdatabase.dao.UserDao
import com.test.androidavp.roomdatabase.entity.RocketEntity
import com.test.androidavp.roomdatabase.entity.User

@Database(entities = [User::class, RocketEntity::class], version = 1)
@TypeConverters(value = [ArrayListConverter::class])
 abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun rocketDao(): RocketDao
}