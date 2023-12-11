package ru.practicum.android.diploma.detail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.detail.data.VacancyDetailRepositoryImpl

val detailModule = module {

    single<VacancyDetailRepository> {
        VacancyDetailRepositoryImpl(networkClient = get())
    }

    factory<DetailVacancyInteractor> {
        DetailVacancyInteractorImpl(repository = get())
    }

    viewModel {
        DetailViewModel(
            get(), get()
        )
    }
}
