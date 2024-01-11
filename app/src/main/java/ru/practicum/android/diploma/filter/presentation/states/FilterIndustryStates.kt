package ru.practicum.android.diploma.filter.presentation.states

import ru.practicum.android.diploma.filter.domain.models.Industry

sealed class FilterIndustryStates {
    object Loading : FilterIndustryStates()
    object Empty : FilterIndustryStates()
    object ServerError : FilterIndustryStates()
    object ConnectionError : FilterIndustryStates()
    data class Success(val industries: List<Industry>) : FilterIndustryStates()
    object HasSelected : FilterIndustryStates()
}
