package com.example.rates.data.model.dto

import com.example.rates.data.model.CurrencyRate
import com.example.rates.data.model.ExchangeRates
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
class GetLatestRatesResponse(
    @SerialName("disclaimer") val disclaimer: String,
    @SerialName("license") val licence: String,
    @SerialName("timestamp") val timestamp: Long,
    @SerialName("base") val base: String,
    @SerialName("rates") val rates: Map<String, Double>,
) {
    fun toExchangeRates(): ExchangeRates {
        val currencyRates = rates
            .map { CurrencyRate(it.key, it.value) }
            .toList()
        return ExchangeRates(currencyRates).apply {
            timestamp = this@GetLatestRatesResponse.timestamp
        }
    }
}