package com.test.androidavp.roomdatabase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.test.androidavp.roomdatabase.entity.RocketEntity

@Dao
interface  RocketDao {
    @Query("SELECT * FROM rocketentity")
    fun getAll(): List<RocketEntity>

    @Insert
    fun insert(urlDto: RocketEntity)
}