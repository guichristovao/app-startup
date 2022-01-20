package com.guichristovao.appstartup.network

import android.util.Log
import com.guichristovao.appstartup.network_support.ExceptionHandler
import com.guichristovao.appstartup.network_support.ServiceCreator
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Network {

    lateinit var serviceCreator: ServiceCreator
        private set
    lateinit var exceptionHandler: ExceptionHandler

    fun init(): ServiceCreator {
        val client = setupOkHttpClient()
        val retrofit = setupRetrofit(client)
        serviceCreator = setupServiceCreator(retrofit)
        exceptionHandler = setupExceptionHandler()

        return serviceCreator
    }

    private fun setupOkHttpClient() = OkHttpClient.Builder().build()

    private fun setupRetrofit(
        client: OkHttpClient
    ) = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .validateEagerly(true)
        .build()

    private fun setupServiceCreator(
        retrofit: Retrofit
    ) = object : ServiceCreator {
        override fun <T : Any> create(clazz: Class<T>): Any {
            return retrofit.create(clazz)
        }
    }

    private fun setupExceptionHandler() = object : ExceptionHandler {
        override fun onFailure(throwable: Throwable) {
            Log.v("app-startup", "Request error: ${throwable.message}")
        }
    }
}
