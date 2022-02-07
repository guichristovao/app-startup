package com.guichristovao.appstartup.network_support

interface ServiceCreator {

    fun <T : Any> create(clazz: Class<T>): Any
}
