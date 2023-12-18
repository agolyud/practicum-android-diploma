package ru.practicum.android.diploma.detail.presentation.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.detail.domain.DetailVacancyInteractor
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Error
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Loading
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.NoConnect
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Success
import ru.practicum.android.diploma.search.domain.models.ResponseCodes

const val VACANCY_ID = "id"

class DetailViewModel(
    private val detailsInterActor: DetailVacancyInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableLiveData<DetailState>(Loading)
    val state = _state

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            //val id = savedStateHandle.get<String>(VACANCY_ID) ?: return@launch
            val resultData = detailsInterActor.execute(idVacancy = "89610081")
            when (resultData.responseCodes) {
                ResponseCodes.ERROR -> {
                    _state.value = Error(resultData.responseCodes.name)
                }

                ResponseCodes.SUCCESS -> {
                    Log.d("TAG result", "result - ${resultData.detailVacancy}")
                    _state.value = resultData.detailVacancy?.let { Success(it) }
                }

                ResponseCodes.NO_NET_CONNECTION -> {
                    _state.value = NoConnect(resultData.responseCodes.name)
                }
            }

        }
    }


}
