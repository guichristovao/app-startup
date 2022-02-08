package com.guichristovao.appstartup.network_support

typealias ExceptionHandler = (Throwable) -> Unit

interface Network {

    fun <T : Any> createService(clazz: Class<T>): T

    fun onFailure(throwable: Throwable)
}
