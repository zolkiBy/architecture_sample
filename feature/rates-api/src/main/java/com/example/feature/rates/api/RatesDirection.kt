package com.example.feature.rates.api

sealed interface RatesDirection {
    object ToAccount : RatesDirection
}