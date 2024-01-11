package ru.practicum.android.diploma.detail.domain.api

import ru.practicum.android.diploma.detail.domain.models.SimilarVacancy
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

interface SimilarRepository {
    suspend fun doRequest(vacancy: String): DtoConsumer<List<SimilarVacancy>>
}
