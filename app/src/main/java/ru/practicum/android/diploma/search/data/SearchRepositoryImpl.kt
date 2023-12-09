package ru.practicum.android.diploma.search.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.diploma.search.data.models.ResponseCodes
import ru.practicum.android.diploma.search.data.models.SearchRequest
import ru.practicum.android.diploma.search.data.models.SearchResponse
import ru.practicum.android.diploma.search.data.models.VacancyDto
import ru.practicum.android.diploma.search.data.models.dto.Salary
import ru.practicum.android.diploma.search.domain.api.DtoConsumer
import ru.practicum.android.diploma.search.domain.api.SearchRepository
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.Vacancy
import ru.practicum.android.diploma.util.network.NetworkClient

class SearchRepositoryImpl(private val networkClient: NetworkClient) : SearchRepository {

    override suspend fun doRequest(
        filter: Filter
    ): Flow<DtoConsumer<List<Vacancy>>> =
        flow {
            val response = networkClient.doRequest(AdapterSearch.filterToVacancyReq(filter))
            when (response.resultCode) {
                ResponseCodes.SUCCESS -> emit(
                    DtoConsumer.Success(
                        AdapterSearch
                            .vacancyInfoDtoToVacancyInfo(
                                response = (response as SearchResponse)
                                    .vacancyList
                            )
                    )
                )
                ResponseCodes.NO_NET_CONNECTION -> emit(DtoConsumer.NoInternet(response.resultCode.code))
                ResponseCodes.ERROR -> emit(DtoConsumer.Error(response.resultCode.code))
            }
        }.flowOn(Dispatchers.IO)
}

object AdapterSearch {
    fun vacancyInfoDtoToVacancyInfo(response: List<VacancyDto>): List<Vacancy> =  response.map {
        Vacancy(
            id =it.id,
            area = it.area.name,
            department = it.department.name,
            employerImgUrl = it.employer.url,
            employer = it.employer.name,
            name = it.name,
            salary = formSalaryString(it.salary),
            type = it.type.name
        )
    }

    fun filterToVacancyReq(filter: Filter) = SearchRequest(
        makeHasMap(filter)
    )

    private fun formSalaryString(salary: Salary?): String {
        if (salary == null) return " "
        return "от  ${salary.from.toString()}  до  ${salary.to.toString()} ${salary.currency}"
    }

    private fun makeHasMap (filter: Filter): HashMap<String,String> {
        val request =HashMap<String,String>()
        request["text"] = filter.request
        if (filter.area != null) request["area"] = filter.area
        if (filter.industry != null) request["industry"] = filter.industry
        if (filter.salary != null) request["area"] = filter.salary.toString()
        return request
    }
}
