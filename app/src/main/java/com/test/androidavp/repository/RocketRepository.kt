package com.test.androidavp.repository

import com.test.androidavp.dto.RocketDetailDto
import com.test.androidavp.dto.RocketListDto
import com.test.androidavp.network.ApiInterface
import com.test.androidavp.roomdatabase.dao.RocketDao
import com.test.androidavp.roomdatabase.database.AppDatabase
import retrofit2.Call
import javax.inject.Inject

class RocketRepository @Inject constructor(
    var apiInterface: ApiInterface,
    var roomDatabase: AppDatabase,
    ) {

    fun callRocketListApi() : Call<List<RocketListDto>> {
        return apiInterface.callRocketListApi("rockets")
    }

    fun callRocketDetailApi(id: String) : Call<RocketDetailDto> {
        return apiInterface.callRocketDetailApi( id)
    }

    fun callRoomDB() : RocketDao {
        return roomDatabase.rocketDao()
    }
}
