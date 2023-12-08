package ru.practicum.android.diploma.search.domain

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.VacancyInfo

interface SearchInteractor {
   suspend fun execute(filter: Filter): Flow<VacancyInfo>
}
