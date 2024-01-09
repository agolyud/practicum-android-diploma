package ru.practicum.android.diploma.detail.presentation.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.detail.domain.DetailVacancyInteractor
import ru.practicum.android.diploma.detail.domain.models.DetailVacancy
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Error
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Loading
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.NoConnect
import ru.practicum.android.diploma.detail.presentation.detail.DetailState.Success
import ru.practicum.android.diploma.favorite.domain.FavoriteInteractor
import ru.practicum.android.diploma.search.domain.models.ResponseCodes

const val VACANCY_ID = "id"

class DetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val detailsInterActor: DetailVacancyInteractor,
    private val favoriteInterActor: FavoriteInteractor,
) : ViewModel() {
    private val _state = MutableLiveData<DetailState>(Loading)
    val state = _state

    private val _stateFavorite = MutableLiveData(false)
    val stateFavorite = _stateFavorite

    init {
        getData()
    }

    fun onFavoriteClick(vacancy: DetailVacancy, setFavorite: Boolean){
        viewModelScope.launch {
            if(setFavorite){
                _stateFavorite.value = true
                favoriteInterActor.addFavorite(vacancy)
            } else {
                _stateFavorite.value = false
                favoriteInterActor.deleteFavorite(vacancy.id)
            }
        }
    }

    private fun getVacancyFromDB(id: String){
        _state.value = Loading
        viewModelScope.launch {
            favoriteInterActor.getFavorite(id).collect{
                if (it.isEmpty()){
                    _state.value = NoConnect(ResponseCodes.NO_NET_CONNECTION.name)
                    _stateFavorite.value = false
                } else {
                    _state.value = Success(it[0])
                    _stateFavorite.value = true
                }
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            val id = savedStateHandle.get<String>(VACANCY_ID) ?: return@launch
            val resultData = detailsInterActor.execute(id)
            when (resultData.responseCodes) {
                ResponseCodes.ERROR -> {
                    _state.value = Error(resultData.responseCodes.name)
                }

                ResponseCodes.SUCCESS -> {
                    _state.value = resultData.detailVacancy?.let { Success(it) }
                    favoriteInterActor.getFavorite(id).collect {
                        _stateFavorite.value = it.isNotEmpty()
                    }
                }

                ResponseCodes.NO_NET_CONNECTION -> {
                    getVacancyFromDB(id)
                    //_state.value = NoConnect(resultData.responseCodes.name)
                }
            }

        }
    }


}
