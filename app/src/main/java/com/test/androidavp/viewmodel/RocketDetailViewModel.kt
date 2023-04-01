package com.test.androidavp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.androidavp.dto.RocketDetailDto
import com.test.androidavp.repository.RocketRepository
import com.test.androidavp.roomdatabase.dao.RocketDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RocketDetailViewModel @Inject constructor(
    private val rocketRepository: RocketRepository
): ViewModel(){

    var rocketDetailState = MutableLiveData<RocketDetailDto>()
    var failureState = MutableLiveData<String>()
    var rocketDaoState = MutableLiveData<RocketDao>()

    fun callRocketDetail(id: String) {
        val url = rocketRepository.callRocketDetailApi(id)
        url.enqueue(object : Callback<RocketDetailDto> {
            override fun onResponse(call: Call<RocketDetailDto>, response: Response<RocketDetailDto>) {
                rocketDetailState.postValue(response.body())
            }

            override fun onFailure(call: Call<RocketDetailDto>, t: Throwable) {
                failureState.postValue(t.message)
            }

        })
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