package ru.practicum.android.diploma.search.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.impl.FilterInteractor
import ru.practicum.android.diploma.search.domain.SearchInteractor
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.ResponseCodes
import ru.practicum.android.diploma.search.domain.models.VacancyInfo
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val filterInteractor: FilterInteractor,

) : ViewModel() {

    private var filter: Filter = Filter(
        page = 0,
        request = "",
        area = "",
        industry = "",
        salary = 0,
        onlyWithSalary = false
    )
    private var state: SearchStates = SearchStates.Default
    private val stateLiveData = MutableLiveData(state)

    fun loadVacancy(request: String) {
        filter.request = request
        if (filter.request.isBlank()) return
        stateLiveData.value = SearchStates.Loading
        viewModelScope.launch {
            searchInteractor.execute(filter = filter).collect { jobsInfo ->
                changeState(jobsInfo)
            }
        }
    }

    fun getState(): LiveData<SearchStates> = stateLiveData

    fun getFilterSettings(){
        viewModelScope.launch {
            val filterSettings = filterInteractor.getFilterSettings()
            if (filterSettings != null) {
                val salary = if (filterSettings.salary.isNotEmpty() &&
                    Integer.parseInt(filterSettings.salary) > 0) {
                    Integer.parseInt(filterSettings.salary)
                } else {
                    0
                }
                val onlyWithSalary = filterSettings.onlyWithSalary
                val industry = if (!filterSettings.industry.id.isNullOrEmpty() ) {
                    filterSettings.industry.id
                } else {
                    ""
                }
                val area = if (!filterSettings.region.id.isNullOrEmpty()) {
                    filterSettings.region.id
                } else {
                    if (!filterSettings.region.id.isNullOrEmpty()) {
                        filterSettings.country.id
                    } else {
                        ""
                    }
                }
                filter = Filter (
                    page = 0,
                    request = filter.request,
                    area = area,
                    industry = industry,
                    salary = salary,
                    onlyWithSalary = onlyWithSalary
                )
            } else {
                filter = Filter (
                    page = 0,
                    request = filter.request,
                    area = "",
                    industry = "",
                    salary = 0,
                    onlyWithSalary = false
                )
            }
        }
    }

    private fun changeState(vacancyInfo: VacancyInfo) =
        when (vacancyInfo.responseCodes) {
            ResponseCodes.ERROR -> {
                state = SearchStates.ServerError
                stateLiveData.value = state
                Log.d("server error", vacancyInfo.responseCodes.name)
            }

            ResponseCodes.SUCCESS -> {
                state =
                    vacancyInfo.vacancy?.let { SearchStates.Success(it) } ?: SearchStates.InvalidRequest
                stateLiveData.value = state
                Log.d("success", vacancyInfo.responseCodes.name)
            }

            ResponseCodes.NO_NET_CONNECTION -> {
                state = SearchStates.ConnectionError
                stateLiveData.value = state
                Log.d("internet error", vacancyInfo.responseCodes.name)
            }
        }

    fun hasFilter() {
        viewModelScope.launch {
            val filterSettings = filterInteractor.getFilterSettings()
            if (filterSettings != null) {
                if(filterSettings.region.id.isNotEmpty() ||
                    filterSettings.country.id.isNotEmpty() ||
                    filterSettings.industry.id.isNotEmpty() ||
                    filterSettings.onlyWithSalary ||
                    (!filterSettings.salary.isNullOrEmpty() &&
                     Integer.getInteger(filterSettings.salary) != null &&
                     Integer.parseInt(filterSettings.salary) > 0)
                ) {
                    getFilterSettings()
                    state = SearchStates.HasFilter(true)
                } else {
                    state = SearchStates.HasFilter(false)
                }
            } else {
                state = SearchStates.HasFilter(false)
            }
            stateLiveData.value = state
        }
    }
}
