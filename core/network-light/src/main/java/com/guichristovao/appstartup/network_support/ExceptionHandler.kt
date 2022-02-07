package com.guichristovao.appstartup.network_support

interface ExceptionHandler {

    operator fun invoke(throwable: Throwable)
}
