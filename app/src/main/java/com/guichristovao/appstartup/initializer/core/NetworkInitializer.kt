package com.guichristovao.appstartup.initializer.core

import android.content.Context
import androidx.startup.Initializer
import com.guichristovao.appstartup.network.Network
import com.guichristovao.appstartup.network_impl.NetworkImpl

class NetworkInitializer : Initializer<Network> {
    override fun create(context: Context): Network {
        return NetworkImpl
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
