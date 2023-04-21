package com.example.mvicompose.di

import com.example.base.di.NAME_DISPATCHER_IO
import com.example.base.di.NAME_DISPATCHER_MAIN
import com.example.base.network.interceptors.AuthInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val commonModule = module {
    single(named(NAME_DISPATCHER_MAIN)) { Dispatchers.Main } bind CoroutineDispatcher::class
    single(named(NAME_DISPATCHER_IO)) { Dispatchers.IO } bind CoroutineDispatcher::class
}

val networkModule = module {
    single { provideHttpClient() }
    single { provideRetrofit(okHttpClient = get()) }
}

private fun provideHttpClient(): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

@OptIn(ExperimentalSerializationApi::class)
private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl("https://openexchangerates.org/api/")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .client(okHttpClient)
        .build()
