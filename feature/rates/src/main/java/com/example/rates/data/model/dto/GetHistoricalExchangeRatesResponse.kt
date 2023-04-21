package com.example.rates.data.model.dto

import com.example.rates.data.model.CurrencyRate
import com.example.rates.data.model.ExchangeRates

class GetHistoricalExchangeRatesResponse : BaseRatesResponse() {

    fun toExchangeRates(): ExchangeRates? {
        val currencyRates = rates
            ?.map { CurrencyRate(it.key, it.value) }
            ?.toList()

        return if (currencyRates != null) {
            ExchangeRates(currencyRates).apply {
                timestamp = this@GetHistoricalExchangeRatesResponse.timestamp
            }
        } else {
            null
        }
    }
}
