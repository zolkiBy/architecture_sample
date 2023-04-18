package com.example.rates.data.model

data class ExchangeRates(val rates: List<CurrencyRate>) {
    var timestamp: Long? = null
}