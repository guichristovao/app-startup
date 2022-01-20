package com.guichristovao.appstartup.network_support

interface ExceptionHandler {

    fun onFailure(throwable: Throwable)
}
