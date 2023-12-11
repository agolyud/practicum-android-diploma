package ru.practicum.android.diploma.detail.domain.api

import ru.practicum.android.diploma.detail.domain.models.DetailVacancy
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

interface VacancyDetailRepository {
    suspend fun doRequest(idVacancy: String): DtoConsumer<DetailVacancy>
}
