package ru.practicum.android.diploma.detail.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.detail.data.VacancyDetailRepositoryImpl
import ru.practicum.android.diploma.detail.domain.DetailVacancyInteractor
import ru.practicum.android.diploma.detail.domain.api.VacancyDetailRepository
import ru.practicum.android.diploma.detail.domain.impl.DetailVacancyInteractorImpl


val detailModule = module {

    single<VacancyDetailRepository> {
        VacancyDetailRepositoryImpl(networkClient = get())
    }

    factory<DetailVacancyInteractor> {
        DetailVacancyInteractorImpl(repository = get())
    }

}
