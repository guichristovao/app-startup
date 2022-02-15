package com.guichristovao.appstartup.profile

import com.guichristovao.appstartup.network.Network

object ProfileFeature {

    lateinit var network: Network
        private set

    fun init(network: Network) {
        this.network = network
    }
}
