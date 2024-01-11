package ru.practicum.android.diploma.detail.domain

import ru.practicum.android.diploma.detail.domain.models.SimilarVacanciesInfo

interface SimilarInteractor {
    suspend fun execute(vacancy: String): SimilarVacanciesInfo
}
