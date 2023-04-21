package com.example.rates.data.net

import com.example.rates.common.annotations.DateFormat
import com.example.rates.data.model.dto.GetHistoricalExchangeRatesResponse
import com.example.rates.data.model.dto.GetLatestRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

private const val PATH_DATE = "date"

interface ExchangeRatesApi {

    @GET("latest.json")
    suspend fun getLatestRates(): Response<GetLatestRatesResponse>

    @GET("historical/{${PATH_DATE}}.json")
    suspend fun getHistoricalExchangeRate(@Path(PATH_DATE) @DateFormat date: String): Response<GetHistoricalExchangeRatesResponse>
}