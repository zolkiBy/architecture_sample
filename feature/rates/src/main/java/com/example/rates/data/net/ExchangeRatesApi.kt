package com.example.rates.data.net

import com.example.rates.data.model.dto.GetLatestRatesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ExchangeRatesApi {

    @GET("latest.json")
    suspend fun getLatestRates(): Response<GetLatestRatesResponse>
}