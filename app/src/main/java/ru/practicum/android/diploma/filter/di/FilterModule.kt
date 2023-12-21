package ru.practicum.android.diploma.filter.di

import org.koin.dsl.module
import ru.practicum.android.diploma.detail.domain.SimilarInteractor
import ru.practicum.android.diploma.detail.domain.impl.SimilarInteractorImpl
import ru.practicum.android.diploma.filter.data.FilterRepositoryImpl
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.impl.FilterInteractor
import ru.practicum.android.diploma.filter.domain.impl.FilterInteractorImpl

val filterModule = module {
    single<FilterRepository> {
        FilterRepositoryImpl(get(), get())
    }

    factory<FilterInteractor> {
        FilterInteractorImpl(repository = get())
    }
}
