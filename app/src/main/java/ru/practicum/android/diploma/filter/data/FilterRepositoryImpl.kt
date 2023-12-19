package ru.practicum.android.diploma.filter.data

import ru.practicum.android.diploma.filter.data.converter.AreaConverter
import ru.practicum.android.diploma.filter.data.converter.FilterSettingsConverter
import ru.practicum.android.diploma.filter.data.converter.IndustryConverter
import ru.practicum.android.diploma.filter.domain.api.FilterRepository
import ru.practicum.android.diploma.filter.domain.models.FilterSettings
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.search.data.models.Industry
import ru.practicum.android.diploma.util.storage.sharedpreference.SharedPrefStorageClient

class FilterRepositoryImpl(
    private val storageClient: SharedPrefStorageClient
): FilterRepository {
    override suspend fun saveCountryFilter(country: String) {
        storageClient.saveCountry(country)
    }

    override suspend fun deleteCountryFilter() {
        storageClient.deleteCountry()
    }

    override suspend fun saveAreaFilter(area: Region) {
        storageClient.saveArea(AreaConverter.map(area))
    }

    override suspend fun deleteAreaFilter() {
        storageClient.deleteArea()
    }

    override suspend fun saveIndustryFilter(industry: Industry) {
        storageClient.saveIndustry(IndustryConverter.map(industry))
    }

    override suspend fun deleteIndustryFilter() {
        storageClient.deleteIndustry()
    }

    override suspend fun setFilter(salary: String?, onlyWithSalary: Boolean) {
        storageClient.setFilter(salary, onlyWithSalary)
    }

    override suspend fun clearFilter() {
        storageClient.clearFilter()
    }

    override suspend fun getFilter(): FilterSettings {
        return FilterSettingsConverter().map(storageClient.getFilter())
    }
}
