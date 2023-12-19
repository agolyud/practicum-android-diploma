package ru.practicum.android.diploma.filter.di

import org.koin.dsl.module
import ru.practicum.android.diploma.filter.data.FilterRepositoryImpl
import ru.practicum.android.diploma.filter.domain.api.FilterRepository

val filterModule = module {
    single<FilterRepository> {
        FilterRepositoryImpl(get(), get())
    }
}
