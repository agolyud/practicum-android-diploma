package ru.practicum.android.diploma.util.storage.sharedpreference

import ru.practicum.android.diploma.filter.data.model.FilterSettingsDto
import ru.practicum.android.diploma.filter.data.model.IndustryDto
import ru.practicum.android.diploma.filter.data.model.RegionDto

interface StorageClient {
    fun saveCountry(country: String)
    fun deleteCountry()
    fun saveArea(area: RegionDto)
    fun deleteArea()
    fun saveIndustries(industries: ArrayList<IndustryDto>)
    fun deleteIndustries()
    fun setFilter(salary: String?, onlyWithSalary: Boolean)
    fun clearFilter()
    fun getFilter(): FilterSettingsDto
}
