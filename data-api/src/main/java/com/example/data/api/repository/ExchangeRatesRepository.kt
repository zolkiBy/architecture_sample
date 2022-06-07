package com.example.data.api.repository

import com.example.data.api.model.ExchangeRates

interface ExchangeRatesRepository {
    suspend fun getLastExchangeRates(): ExchangeRates
}