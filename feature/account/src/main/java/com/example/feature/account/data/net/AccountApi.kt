package com.example.feature.account.data.net

import com.example.feature.account.data.model.dto.GetUsageResponse
import retrofit2.Response
import retrofit2.http.GET

interface AccountApi {

    @GET("usage.json")
    suspend fun getUsage(): Response<GetUsageResponse>
}