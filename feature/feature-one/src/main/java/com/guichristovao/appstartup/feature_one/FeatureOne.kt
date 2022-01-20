package com.guichristovao.appstartup.feature_one

import com.guichristovao.appstartup.network_support.ServiceCreator

object FeatureOne {

    lateinit var serviceCreator: ServiceCreator

    fun init(serviceCreator: ServiceCreator) {
        this.serviceCreator = serviceCreator
    }
}
