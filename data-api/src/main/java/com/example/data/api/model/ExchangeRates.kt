package com.example.data.api.model

data class ExchangeRates(val rates: List<CurrencyRate>) {
    var timestamp: Long? = null
}