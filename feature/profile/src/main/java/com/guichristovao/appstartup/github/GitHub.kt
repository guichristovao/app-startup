package com.guichristovao.appstartup.github

import com.guichristovao.appstartup.network_support.ExceptionHandler
import com.guichristovao.appstartup.network_support.ServiceCreator

object GitHub {

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
