package ru.practicum.android.diploma.search.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.search.domain.SearchInteractor
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.ResponseCodes
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.search.domain.models.VacancyInfo

class SearchInteractorImpl(val repository: SearchRepository) : SearchInteractor {
    override suspend fun execute(filter: Filter): Flow<VacancyInfo> =
        repository.doRequest(filter = filter).map { result ->
            when (result) {
                is DtoConsumer.Success -> Adapter.searchInfoCreator(
                    responseCodes = ResponseCodes.SUCCESS,
                    result.data
                )

                is DtoConsumer.Error -> Adapter.searchInfoCreator(ResponseCodes.ERROR, null)

                is DtoConsumer.NoInternet -> Adapter.searchInfoCreator(ResponseCodes.NO_NET_CONNECTION, null)
            }
        }
}

object Adapter {
    fun searchInfoCreator(responseCodes: ResponseCodes, vacancy: List<Vacancy>?) = VacancyInfo(
        responseCodes = responseCodes,
        vacancy
    )
}
