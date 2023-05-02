package com.example.mvicompose.di

import com.example.mvicompose.App
import org.koin.dsl.module

val appModule = module {
    single { App.instance.nonCancellableAppScope }
}
