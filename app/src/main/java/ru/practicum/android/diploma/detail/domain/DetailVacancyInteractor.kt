package ru.practicum.android.diploma.detail.domain

import ru.practicum.android.diploma.detail.domain.models.DetailVacancyInfo

interface DetailVacancyInteractor {
    suspend fun execute(idVacancy: String): DetailVacancyInfo
}
