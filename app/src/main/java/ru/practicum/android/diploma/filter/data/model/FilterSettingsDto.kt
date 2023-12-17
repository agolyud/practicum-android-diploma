package ru.practicum.android.diploma.filter.data.model

data class FilterSettingsDto(
    val salary: String?,
    val onlyWithSalary: Boolean,
    val industry: IndustryDto,
    val country: String?,
    val area: RegionDto
)
