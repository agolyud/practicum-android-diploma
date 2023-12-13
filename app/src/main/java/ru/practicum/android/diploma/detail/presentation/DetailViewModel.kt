package ru.practicum.android.diploma.detail.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.detail.domain.DetailVacancyInteractor
import ru.practicum.android.diploma.detail.domain.models.DetailVacancy
import ru.practicum.android.diploma.favorite.domain.FavoriteInteractor
import ru.practicum.android.diploma.search.domain.models.ResponseCodes

class DetailViewModel(
    private val id: String,
    private val detailsInterActor: DetailVacancyInteractor,
    private val favoriteInterActor: FavoriteInteractor,
) : ViewModel() {
    private val _state = MutableLiveData<DetailState>(DetailState.Loading)
    val state = _state

    init {
        getData()
    }

    fun onFavoriteClick(vacancy: DetailVacancy, setFavorite: Boolean){
        viewModelScope.launch {
            if(setFavorite){
                favoriteInterActor.addFavorite(vacancy)
                _state.value = DetailState.IsFavorite(true)
            } else {
                favoriteInterActor.deleteFavorite(id)
                _state.value = DetailState.IsFavorite(false)
            }
        }
    }

    fun isFavorite(id: String){
        viewModelScope.launch {
            favoriteInterActor.getFavorite(id).collect{
                if (it.isEmpty()){
                    _state.value = DetailState.IsFavorite(false)
                } else {
                    _state.value = DetailState.IsFavorite(true)
                }
            }
        }
    }

    private fun getData() {
        viewModelScope.launch {
            val resultData = detailsInterActor.execute(id)
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
