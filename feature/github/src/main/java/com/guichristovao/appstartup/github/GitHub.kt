package com.guichristovao.appstartup.github

import com.guichristovao.appstartup.network_support.ServiceCreator

object GitHub {

    lateinit var serviceCreator: ServiceCreator
        private set

    fun init(serviceCreator: ServiceCreator) {
        this.serviceCreator = serviceCreator
    }
}
