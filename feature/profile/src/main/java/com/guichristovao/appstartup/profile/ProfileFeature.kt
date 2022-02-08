package com.guichristovao.appstartup.profile

import com.guichristovao.appstartup.network_support.Network

object ProfileFeature {

    lateinit var network: Network
        private set

    fun init(network: Network) {
        this.network = network
    }
}
