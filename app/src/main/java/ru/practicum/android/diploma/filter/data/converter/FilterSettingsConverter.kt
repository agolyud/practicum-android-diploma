package ru.practicum.android.diploma.filter.data.converter

import ru.practicum.android.diploma.filter.data.model.FilterSettingsDto
import ru.practicum.android.diploma.filter.domain.models.FilterSettings

class FilterSettingsConverter {
    fun map(filterSettings: FilterSettingsDto): FilterSettings{
        return FilterSettings(
            salary = filterSettings.salary,
            onlyWithSalary = filterSettings.onlyWithSalary,
            industries = IndustryConverter.mapFromDto(filterSettings.industries) ,
            country = filterSettings.country,
            area = AreaConverter.map(filterSettings.area)
        )
    }

    fun map(filterSettings: FilterSettings): FilterSettingsDto{
        return FilterSettingsDto(
            salary = filterSettings.salary,
            onlyWithSalary = filterSettings.onlyWithSalary,
            industries = IndustryConverter.mapToDto(filterSettings.industries),
            country = filterSettings.country,
            area = AreaConverter.map(filterSettings.area)
        )
    }
}
