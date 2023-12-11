package ru.practicum.android.diploma.util.network

import org.koin.dsl.module

val netModule = module {
    single<InternetConnectionValidator> {
        InternetConnectionValidator(get())
    }

    single<RetrofitNetworkClient> {
        RetrofitNetworkClient(get())
    }
}
