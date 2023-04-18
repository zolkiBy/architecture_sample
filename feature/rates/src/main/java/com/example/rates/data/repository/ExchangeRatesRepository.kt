package com.example.rates.data.repository

import com.example.base.common.result.Result
import com.example.rates.data.model.ExchangeRates
import kotlinx.coroutines.flow.Flow

interface ExchangeRatesRepository {
    suspend fun getLastExchangeRates(): Flow<Result<ExchangeRates, Exception>>
}