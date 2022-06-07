package com.example.mvicompose.di

import com.example.data.api.repository.ExchangeRatesRepository
import com.example.data.net.AuthInterceptor
import com.example.data.net.ExchangeRatesApi
import com.example.data.repository.ExchangeRatesRepositoryImpl
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    single { provideHttpClient() }
    single { provideRetrofit(okHttpClient = get()) }
    single { provideExchangeRatesApi(retrofit = get()) }

    single { ExchangeRatesRepositoryImpl(ratesApi = get()) } bind ExchangeRatesRepository::class
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

private fun provideExchangeRatesApi(retrofit: Retrofit): ExchangeRatesApi {
    return retrofit.create(ExchangeRatesApi::class.java)
}
