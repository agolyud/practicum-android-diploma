package ru.practicum.android.diploma.util.storage.sharedpreference

import ru.practicum.android.diploma.filter.data.model.FilterSettingsDto
import ru.practicum.android.diploma.filter.data.model.IndustryDto
import ru.practicum.android.diploma.filter.data.model.RegionDto

interface StorageClient {
    suspend fun saveCountry(country: String)
    suspend fun deleteCountry()
    suspend fun saveArea(area: RegionDto)
    suspend fun deleteArea()
    suspend fun saveIndustry(industry: IndustryDto)
    suspend fun deleteIndustry()
    suspend fun setFilter(salary: String?, onlyWithSalary: Boolean)
    suspend fun clearFilter()
    suspend fun getFilter(): FilterSettingsDto
}
