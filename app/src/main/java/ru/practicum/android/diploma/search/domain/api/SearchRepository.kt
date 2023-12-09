package ru.practicum.android.diploma.search.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Vacancy

interface SearchRepository {

    suspend fun doRequest(filter: Filter): Flow<DtoConsumer<List<Vacancy>>>
}
