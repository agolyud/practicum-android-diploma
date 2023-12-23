package ru.practicum.android.diploma.filter.data.converter

import ru.practicum.android.diploma.filter.data.model.FilterSettingsDto
import ru.practicum.android.diploma.filter.domain.models.FilterSettings

class FilterSettingsConverter {
    fun map(filterSettings: FilterSettingsDto): FilterSettings{
        return FilterSettings(
            salary = filterSettings.salary,
            onlyWithSalary = filterSettings.onlyWithSalary,
            industry = IndustryConverter.map(filterSettings.industry),
            country = filterSettings.country,
            area = RegionConverter.map(filterSettings.area)
        )
    }

    fun map(filterSettings: FilterSettings): FilterSettingsDto{
        return FilterSettingsDto(
            salary = filterSettings.salary,
            onlyWithSalary = filterSettings?.onlyWithSalary,
            industry = IndustryConverter.map(filterSettings.industry),
            country = filterSettings.country,
            area = RegionConverter.map(filterSettings.area)
        )
    }
}
