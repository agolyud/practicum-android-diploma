package ru.practicum.android.diploma.filter.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.domain.models.FilterSettings
import ru.practicum.android.diploma.filter.domain.models.Industry
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.search.domain.api.DtoConsumer

interface FilterRepository {
    suspend fun saveCountryFilter(country: Country)
    suspend fun deleteCountryFilter()
    suspend fun getCountryFilter(): Country
    suspend fun saveRegionFilter(region: Region)
    suspend fun deleteRegionFilter()
    suspend fun getRegionFilter(): Region
    suspend fun saveIndustryFilter(industry: Industry)
    suspend fun deleteIndustryFilter()
    suspend fun getIndustryFilter(): Industry
    suspend fun setFilter(salary: String, onlyWithSalary: Boolean)
    suspend fun clearFilter()
    suspend fun getFilter(): FilterSettings?
    suspend fun getIndustries(): Flow<DtoConsumer<List<Industry>>>
    suspend fun getCountries(): Flow<DtoConsumer<List<Country>>>
    suspend fun getRegions(): Flow<DtoConsumer<List<Region>>>
    suspend fun getRegionsByName(name: String): Flow<DtoConsumer<List<Region>>>
    suspend fun getIndustriesByName(industry: String): Flow<DtoConsumer<List<Industry>>>
}
