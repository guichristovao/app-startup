package com.guichristovao.appstartup.initializer.feature

import android.content.Context
import androidx.startup.Initializer
import com.guichristovao.appstartup.initializer.core.NetworkInitializer
import com.guichristovao.appstartup.network.NetworkImpl
import com.guichristovao.appstartup.profile.ProfileFeature

class ProfileInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        ProfileFeature.init(NetworkImpl)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return listOf(NetworkInitializer::class.java)
    }
}
