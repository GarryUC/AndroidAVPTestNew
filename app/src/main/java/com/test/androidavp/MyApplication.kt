package com.test.androidavp

import android.app.Application
import com.test.androidavp.network.NetworkModule
import com.test.androidavp.ui.activity.MainActivity
import com.test.androidavp.ui.activity.RocketDetailActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class, AppModule::class])
interface ApplicationComponent {
    fun inject(loginActivity: MainActivity)
    fun inject(loginActivity: RocketDetailActivity)

}

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent : ApplicationComponent
    }
}