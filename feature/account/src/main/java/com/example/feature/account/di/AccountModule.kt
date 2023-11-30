package com.example.feature.account.di

import com.example.base.di.NAME_DISPATCHER_IO
import com.example.feature.account.data.net.AccountApi
import com.example.feature.account.data.repository.AccountRepository
import com.example.feature.account.data.repository.AccountRepositoryImpl
import com.example.feature.account.domain.ChangeAccountDataUseCase
import com.example.feature.account.domain.DeleteAccountDataUseCase
import com.example.feature.account.domain.GetAccountDataUseCase
import com.example.feature.account.domain.LoadAndSaveAccountDataUseCase
import com.example.feature.account.presentation.AccountViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accountModule = module {
    single { provideAccountApi(retrofit = get()) }
    single<AccountRepository> {
        AccountRepositoryImpl(
            accountApi = get(),
            accountDataDao = get(),
            coroutineDispatcher = get(named(NAME_DISPATCHER_IO))
        )
    }
    factory {
        LoadAndSaveAccountDataUseCase(
            coroutineDispatcher = get(named(NAME_DISPATCHER_IO)),
            applicationScope = get(),
            accountRepository = get(),
        )
    }
    factory {
        DeleteAccountDataUseCase(
            coroutineDispatcher = get(named(NAME_DISPATCHER_IO)),
            applicationScope = get(),
            accountRepository = get(),
        )
    }
    factory {
        GetAccountDataUseCase(
            coroutineDispatcher = get(named(NAME_DISPATCHER_IO)),
            accountRepository = get(),
            deleteAccountDataUseCase = get(),
            loadAndSaveAccountDataUseCase = get(),
        )
    }
    factory {
        LoadAndSaveAccountDataUseCase(
            coroutineDispatcher = get(named(NAME_DISPATCHER_IO)),
            applicationScope = get(),
            accountRepository = get(),
        )
    }
    factory {
        ChangeAccountDataUseCase(
            coroutineDispatcher = get(named(NAME_DISPATCHER_IO)),
            applicationScope = get(),
            accountRepository = get(),
        )
    }
    viewModel {
        AccountViewModel(
            getAccountDataUseCase = get(),
            changeAccountDataUseCase = get()
        )
    }
}

private fun provideAccountApi(retrofit: Retrofit): AccountApi =
    retrofit.create(AccountApi::class.java)
