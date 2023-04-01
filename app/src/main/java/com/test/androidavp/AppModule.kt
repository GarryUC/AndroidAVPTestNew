package com.test.androidavp

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.test.androidavp.roomdatabase.database.AppDatabase
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
internal class AppModule(private val mApplication: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return mApplication
    }

    @Provides
    @Singleton
    fun getRoomDataBase(): AppDatabase {
        return  Room.databaseBuilder(
            mApplication,
            AppDatabase::class.java, "rocket"
        ).allowMainThreadQueries().build()

    }

    @Singleton
    @Provides
    fun provideRoomDataBase(): RoomDatabase {
        return Room.databaseBuilder(
            mApplication,
            AppDatabase::class.java, "user"
        ).allowMainThreadQueries().build()
    }
}