package ru.practicum.android.diploma.filter.domain.models

data class FilterSettings(
    val salary: String,
    val onlyWithSalary: Boolean,
    val industry: Industry,
    val country: Country,
    val region: Region
)
