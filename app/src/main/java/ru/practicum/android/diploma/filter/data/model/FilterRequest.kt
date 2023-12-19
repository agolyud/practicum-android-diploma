package ru.practicum.android.diploma.filter.data.model

sealed class FilterRequest {
    object Industries

    object Countries

    object Regions

    data class RegionsByCountry(val countryId: String)
}
