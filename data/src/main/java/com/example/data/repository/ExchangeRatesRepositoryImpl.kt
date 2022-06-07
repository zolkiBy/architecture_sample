package com.example.data.repository

import com.example.data.api.model.ExchangeRates
import com.example.data.api.repository.ExchangeRatesRepository
import com.example.data.net.ExchangeRatesApi

class ExchangeRatesRepositoryImpl(private val ratesApi: ExchangeRatesApi) : ExchangeRatesRepository {
    // TODO: make two data sources - remote and local
    override suspend fun getLastExchangeRates(): ExchangeRates {
        return ratesApi.getLatestRates().toExchangeRates()
    }
}