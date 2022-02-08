package com.guichristovao.appstartup.initializer.core

import android.content.Context
import androidx.startup.Initializer
import com.guichristovao.appstartup.network.NetworkImpl
import com.guichristovao.appstartup.network_support.Network

class NetworkInitializer : Initializer<Network> {
    override fun create(context: Context): Network {
        return NetworkImpl
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
