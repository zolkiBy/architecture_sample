package com.example.base.navigation.api

interface Navigator<DIRECTION> {
    fun navigate(direction: DIRECTION)
}