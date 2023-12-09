package ru.practicum.android.diploma.util

import org.koin.dsl.module
import ru.practicum.android.diploma.util.network.NetworkClient
import ru.practicum.android.diploma.util.network.RetrofitNetworkClient

val UtilModule = module {
    single<NetworkClient> {
        RetrofitNetworkClient(validator = get())
    }
}
