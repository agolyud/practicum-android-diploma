package ru.practicum.android.diploma.filter.domain.api

import ru.practicum.android.diploma.filter.domain.models.FilterSettings
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.search.data.models.Industry

interface FilterRepository {
    suspend fun saveCountryFilter(country: String)
    suspend fun deleteCountryFilter()
    suspend fun saveAreaFilter(area: Region)
    suspend fun deleteAreaFilter()
    suspend fun saveIndustryFilter(industry: Industry)
    suspend fun deleteIndustryFilter()
    suspend fun setFilter(salary: String?, onlyWithSalary: Boolean)
    suspend fun clearFilter()
    suspend fun getFilter(): FilterSettings
}
