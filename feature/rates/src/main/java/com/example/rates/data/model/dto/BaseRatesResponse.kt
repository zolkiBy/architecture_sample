package com.example.rates.data.model.dto

import kotlinx.serialization.SerialName

open class BaseRatesResponse(
    @SerialName("timestamp") val timestamp: Long? = null,
    @SerialName("base") val base: String? = null,
    @SerialName("rates") val rates: Map<String, Double>? = null,
    @SerialName("error") val error: Boolean? = null,
    @SerialName("status") val status: Int? = null,
    @SerialName("message") val message: String? = null,
    @SerialName("description") val description: String? = null,
) {
    fun isSuccess(): Boolean {
        return error == null || error == false
    }

    fun isError(): Boolean {
        return error != null && error == true
    }
}