package ru.practicum.android.diploma.filter.data.model

data class FilterSettingsDto(
    val salary: String,
    val onlyWithSalary: Boolean,
    val industry: IndustryDto,
    val country: CountryDto,
    val area: RegionDto
)
