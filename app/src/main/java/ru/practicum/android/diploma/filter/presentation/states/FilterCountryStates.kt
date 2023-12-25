package ru.practicum.android.diploma.filter.presentation.states

import ru.practicum.android.diploma.filter.domain.models.Country

sealed class FilterCountryStates {
    object Loading : FilterCountryStates()
    object Empty : FilterCountryStates()
    object ServerError : FilterCountryStates()
    object ConnectionError : FilterCountryStates()
    data class Success(val countries: List<Country>) : FilterCountryStates()
}
