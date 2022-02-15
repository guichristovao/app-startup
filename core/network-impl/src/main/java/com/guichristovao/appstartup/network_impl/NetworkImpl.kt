package com.guichristovao.appstartup.network_impl

import android.util.Log
import com.guichristovao.appstartup.network.Network
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkImpl : Network {

    private var client: OkHttpClient
    private var retrofit: Retrofit

    init {
        client = setupOkHttpClient()
        retrofit = setupRetrofit(client)
    }

    override fun <T : Any> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    override fun onFailure(throwable: Throwable) {
        Log.v("app-startup", "Error: ${throwable.message}")
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
}
