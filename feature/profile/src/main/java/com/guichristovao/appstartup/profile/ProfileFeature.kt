package com.guichristovao.appstartup.profile

import com.guichristovao.appstartup.network_support.ExceptionHandler
import com.guichristovao.appstartup.network_support.ServiceCreator

object ProfileFeature {

    lateinit var serviceCreator: ServiceCreator
        private set

    lateinit var exceptionHandler: ExceptionHandler
        private set

    fun init(
        serviceCreator: ServiceCreator,
        exceptionHandler: ExceptionHandler
    ) {
        this.serviceCreator = serviceCreator
        this.exceptionHandler = exceptionHandler
    }
}
