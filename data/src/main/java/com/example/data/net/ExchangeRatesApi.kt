package com.example.data.net

import com.example.data.model.dto.GetLatestRatesResponse
import retrofit2.http.GET

interface ExchangeRatesApi {

    @GET("latest.json")
    suspend fun getLatestRates(): GetLatestRatesResponse
}