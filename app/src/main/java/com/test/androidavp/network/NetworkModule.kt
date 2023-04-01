package com.test.androidavp.network

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
//import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


// @Module informs Dagger that this class is a Dagger Module
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    fun baseUrl(): String {
        return "https://api.spacexdata.com/v4/"
    }

    @Provides
    fun provideRetrofitService(moshi: Moshi, baseUrl: String) : ApiInterface {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiInterface::class.java)
    }
}