package ru.practicum.android.diploma.filter.data.model

data class FilterSettingsDto(
    val salary: String,
    val onlyWithSalary: Boolean,
    var industry: IndustryDto,
    var country: CountryDto,
    var region: RegionDto
)
