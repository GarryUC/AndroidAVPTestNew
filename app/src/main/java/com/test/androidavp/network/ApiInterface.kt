package com.test.androidavp.network

import com.test.androidavp.dto.RocketDetailDto
import com.test.androidavp.dto.RocketListDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiInterface {
    @GET
    fun callRocketListApi(
        @Url url: String): Call<List<RocketListDto>>

    @GET("rockets/{id}")
    fun callRocketDetailApi(
        @Path(value = "id", encoded = true) storyId: String,
    ): Call<RocketDetailDto>

}