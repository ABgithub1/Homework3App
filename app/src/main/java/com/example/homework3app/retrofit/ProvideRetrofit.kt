package com.example.homework3app.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ProvideRetrofit {

    private val retrofit by lazy(LazyThreadSafetyMode.NONE) { provideRetrofit() }
    val breakingBadApi by lazy(LazyThreadSafetyMode.NONE) {
        retrofit.create<BreakingBadApi>()
    }

    private fun provideRetrofit(): Retrofit {
        val client = OkHttpClient
            .Builder()
            .build()
        return Retrofit.Builder()
            .baseUrl("https://www.breakingbadapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}