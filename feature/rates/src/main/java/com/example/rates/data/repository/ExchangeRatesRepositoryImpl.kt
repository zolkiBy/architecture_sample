package com.example.rates.data.repository

import com.example.base.common.result.Result
import com.example.base.network.exceptions.ApiException
import com.example.rates.common.annotations.DateFormat
import com.example.rates.data.model.ExchangeRates
import com.example.rates.data.net.ExchangeRatesApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber

class ExchangeRatesRepositoryImpl(
    private val ratesApi: ExchangeRatesApi,
    private val coroutineDispatcher: CoroutineDispatcher,
) : ExchangeRatesRepository {
    //TODO: make two data sources - remote and local
    override suspend fun getLastExchangeRates(): Result<ExchangeRates, Exception> {
        return withContext(coroutineDispatcher) {
            val response = ratesApi.getLatestRates()
            val result = if (response.isSuccessful) {
                response.body()?.let { responseBody ->
                    Result.success(responseBody.toExchangeRates())
                } ?: Result.failure(ApiException())
            } else {
                Result.failure(ApiException())
            }

            result
        }
    }

    override suspend fun getHistoricalExchangeRates(@DateFormat date: String): Result<ExchangeRates, Exception> {
        Timber.d("Formatted date before sending request: $date")
        return withContext(coroutineDispatcher) {
            val response = ratesApi.getHistoricalExchangeRate(date)
            val result = if (response.isSuccessful &&
                response.body() != null &&
                response.body()?.isSuccess() == true
            ) {
                response.body()?.toExchangeRates()?.let { rates ->
                    Result.success(rates)
                } ?: Result.failure(ApiException())
            } else {
                Result.failure(ApiException())
            }
            Timber.d("Result of loading: ${result.get()}")
            result
        }
    }
}