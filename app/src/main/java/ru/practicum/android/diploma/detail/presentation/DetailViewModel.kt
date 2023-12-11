package ru.practicum.android.diploma.detail.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.detail.domain.DetailVacancyInteractor
import ru.practicum.android.diploma.search.domain.models.ResponseCodes

const val VACANCY_ID = "id"

class DetailViewModel(
    private val detailsInterActor: DetailVacancyInteractor,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _state = MutableLiveData<DetailState>(DetailState.Loading)
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
                    _state.value = DetailState.Error(resultData.responseCodes.name)
                }

                ResponseCodes.SUCCESS -> {
                    Log.d("TAG result", "result - ${resultData.detailVacancy}")
                    _state.value = resultData.detailVacancy?.let { DetailState.Success(it) }
                }

                ResponseCodes.NO_NET_CONNECTION -> {
                    _state.value = DetailState.NoConnect(resultData.responseCodes.name)
                }
            }

        }
    }


}
