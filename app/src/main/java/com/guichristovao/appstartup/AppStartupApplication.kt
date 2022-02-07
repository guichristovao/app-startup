package com.guichristovao.appstartup

import android.app.Application
import com.guichristovao.appstartup.profile.ProfileFeature
import com.guichristovao.appstartup.network.Network
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppStartupApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Network.init()
        ProfileFeature.init(Network.serviceCreator, Network.exceptionHandler)
    }
}
