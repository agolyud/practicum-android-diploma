package ru.practicum.android.diploma.search.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.search.domain.SearchInteractor
import ru.practicum.android.diploma.search.domain.models.Filter
import ru.practicum.android.diploma.search.domain.models.ResponseCodes
import ru.practicum.android.diploma.search.domain.models.VacancyInfo
import ru.practicum.android.diploma.search.presentation.models.SearchStates

class SearchViewModel(
    private val filter: Filter,
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private var state: SearchStates = SearchStates.Default
    private val stateLiveData = MutableLiveData(state)

    fun loadVacancy() {
        if (filter.request.isBlank()) return
        stateLiveData.value = SearchStates.Loading
        viewModelScope.launch {
            searchInteractor.execute(filter = filter).collect { jobsInfo ->
                changeState(jobsInfo)
            }
        }
    }

    fun getState() = stateLiveData

    private fun changeState(vacancyInfo: VacancyInfo) =
        when (vacancyInfo.responseCodes) {
            ResponseCodes.ERROR -> {
                state = SearchStates.ServerError
                Log.d("server error", vacancyInfo.responseCodes.name)
            }

            ResponseCodes.SUCCESS -> {
                state =
                    vacancyInfo.vacancy?.let { SearchStates.Success(it) } ?: SearchStates.InvalidRequest
                Log.d("success", vacancyInfo.responseCodes.name)
            }

            ResponseCodes.NO_NET_CONNECTION -> {
                state = SearchStates.ConnectionError
                Log.d("internet error", vacancyInfo.responseCodes.name)
            }
        }
}
