package ru.practicum.android.diploma.filter.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.detail.domain.SimilarInteractor
import ru.practicum.android.diploma.detail.domain.impl.SimilarInteractorImpl
import ru.practicum.android.diploma.filter.data.FilterRepositoryImpl
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.impl.FilterInteractor
import ru.practicum.android.diploma.filter.domain.impl.FilterInteractorImpl
import ru.practicum.android.diploma.filter.presentation.viewmodel.FilterIndustryViewModel
import ru.practicum.android.diploma.filter.presentation.viewmodel.FilterRegionViewModel
import ru.practicum.android.diploma.search.presentation.SearchViewModel
import ru.practicum.android.diploma.util.storage.sharedpreference.SharedPrefStorageClient
import ru.practicum.android.diploma.util.storage.sharedpreference.StorageClient

val filterModule = module {

    single<FilterRepository> {
        FilterRepositoryImpl(get(), get())
    }

    factory<FilterInteractor> {
        FilterInteractorImpl(repository = get())
    }

    viewModel {
        FilterIndustryViewModel(
            get()
        )
    }

    viewModel {
        FilterRegionViewModel(
            get()
        )
    }
}
