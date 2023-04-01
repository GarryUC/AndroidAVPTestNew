package com.test.androidavp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.androidavp.dto.RocketListDto
import com.test.androidavp.repository.RocketRepository
import com.test.androidavp.roomdatabase.dao.RocketDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RocketViewModel @Inject constructor(
    private val rocketRepository: RocketRepository
): ViewModel(){

    var dtoState = MutableLiveData<List<RocketListDto>>()
    var rocketDaoState = MutableLiveData<RocketDao>()

    fun callRocketList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val api = rocketRepository.callRocketListApi()
                api.enqueue(object : Callback<List<RocketListDto>> {
                    override fun onResponse(
                        call: Call<List<RocketListDto>>,
                        response: Response<List<RocketListDto>>
                    ) {
                        dtoState.postValue(response.body())
                    }

                    override fun onFailure(call: Call<List<RocketListDto>>, t: Throwable) {

                    }

                })
            }
        }
    }
    fun getRocketDao() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val roomDB = rocketRepository.callRoomDB()
                rocketDaoState.postValue(roomDB)
            }
        }
    }
}