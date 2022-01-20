package com.guichristovao.appstartup

import android.app.Application
import com.guichristovao.appstartup.feature_one.FeatureOne
import com.guichristovao.appstartup.network.Network

class AppStartupApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Network.init()
        FeatureOne.init(Network.serviceCreator)
    }
}
