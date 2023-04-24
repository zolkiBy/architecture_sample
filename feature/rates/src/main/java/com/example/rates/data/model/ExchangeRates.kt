package com.example.rates.data.model

data class ExchangeRates(val rates: List<CurrencyRate>) {
    var timestamp: Long? = null

    companion object {
        fun empty() = ExchangeRates(emptyList())
    }
}