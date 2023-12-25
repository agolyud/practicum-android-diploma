package ru.practicum.android.diploma.filter.presentation.states

import ru.practicum.android.diploma.filter.domain.models.Region

sealed class FilterRegionStates {
    object Loading : FilterRegionStates()
    object Empty : FilterRegionStates()
    object ServerError : FilterRegionStates()
    object ConnectionError : FilterRegionStates()
    data class Success(val regions: List<Region>) : FilterRegionStates()
}
