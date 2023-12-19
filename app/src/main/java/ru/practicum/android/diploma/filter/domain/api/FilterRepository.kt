package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.models.FilterSettings
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.search.data.models.Industry
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

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
    suspend fun getIndustries(): Flow<DtoConsumer<List<Industry>>>
    suspend fun getCountries(): Flow<DtoConsumer<List<Region>>>
    suspend fun getRegions(): Flow<DtoConsumer<List<Region>>>
    suspend fun getRegionsByCountry(countryId: String): Flow<DtoConsumer<List<Region>>>
}
