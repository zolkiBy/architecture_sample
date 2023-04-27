package com.example.rates.data.model.dto

import com.example.base.network.response.BaseResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseRatesResponse(
    @SerialName("timestamp") var timestamp: Long? = null,
    @SerialName("base") var base: String? = null,
    @SerialName("rates") var rates: Map<String, Double>? = null,
) : BaseResponse()