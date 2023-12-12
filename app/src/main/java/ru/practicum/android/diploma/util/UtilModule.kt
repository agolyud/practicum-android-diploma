package ru.practicum.android.diploma.util

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.util.network.InternetConnectionValidator
import ru.practicum.android.diploma.util.network.NetworkClient
import ru.practicum.android.diploma.util.network.RetrofitNetworkClient

val UtilModule = module {

    factory { InternetConnectionValidator(androidContext()) }

    single<NetworkClient> {
        RetrofitNetworkClient(validator = get())
    }
}
