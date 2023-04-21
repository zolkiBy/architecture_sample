package com.example.rates.di

import com.example.base.di.NAME_DISPATCHER_IO
import com.example.rates.data.net.ExchangeRatesApi
import com.example.rates.data.repository.ExchangeRatesRepository
import com.example.rates.data.repository.ExchangeRatesRepositoryImpl
import com.example.rates.domain.GetExchangeRatesUseCase
import com.example.rates.presentation.ExchangeRatesViewModel
import kotlinx.datetime.Clock
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val ratesModule = module {
    factory<Clock> { Clock.System }
    single { provideExchangeRatesApi(retrofit = get()) }
    single<ExchangeRatesRepository> { ExchangeRatesRepositoryImpl(ratesApi = get()) }
    single { GetExchangeRatesUseCase(coroutineDispatcher = get(named(NAME_DISPATCHER_IO)), exchangeRatesRepository = get(), clock = get()) }
    viewModel { ExchangeRatesViewModel(exchangeRatesUseCase = get()) }
}

private fun provideExchangeRatesApi(retrofit: Retrofit): ExchangeRatesApi {
    return retrofit.create(ExchangeRatesApi::class.java)
}