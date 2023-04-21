package com.example.rates.data.repository

import com.example.base.common.result.Result
import com.example.rates.common.annotations.DateFormat
import com.example.rates.data.model.ExchangeRates
import kotlinx.coroutines.flow.Flow

interface ExchangeRatesRepository {
    suspend fun getLastExchangeRates(): Result<ExchangeRates, Exception>

    suspend fun getHistoricalExchangeRates(@DateFormat date: String): Result<ExchangeRates, Exception>
}