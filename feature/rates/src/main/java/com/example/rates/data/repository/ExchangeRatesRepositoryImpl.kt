package com.example.rates.data.repository

import com.example.base.common.result.Result
import com.example.rates.data.model.ExchangeRates
import com.example.rates.data.net.ExchangeRatesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExchangeRatesRepositoryImpl(private val ratesApi: ExchangeRatesApi) : ExchangeRatesRepository {
    //TODO: make two data sources - remote and local
    override suspend fun getLastExchangeRates(): Flow<Result<ExchangeRates, Exception>> = flow {
        val response = ratesApi.getLatestRates()
        val result = if (response.isSuccessful) {
            response.body()?.let { responseBody ->
                Result.success(responseBody.toExchangeRates())
                //TODO: replace generic exception with smth meaningful
            } ?: Result.failure(Exception())
        } else {
            //TODO: replace generic exception with smth meaningful
            Result.failure(Exception())
        }

        emit(result)
    }
}