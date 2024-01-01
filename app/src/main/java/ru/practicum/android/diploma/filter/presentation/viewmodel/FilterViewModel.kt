package ru.practicum.android.diploma.filter.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.filter.domain.impl.FilterInteractor
import ru.practicum.android.diploma.filter.domain.models.Country
import ru.practicum.android.diploma.filter.domain.models.Industry
import ru.practicum.android.diploma.filter.domain.models.Region
import ru.practicum.android.diploma.filter.presentation.states.FilterStates

class FilterViewModel(
    private val filterInteractor: FilterInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<FilterStates>()
    fun getState(): LiveData<FilterStates> = stateLiveData

    fun getFilters() {
        viewModelScope.launch {
            val filter = filterInteractor.getFilter()
            val filterSettings = filterInteractor.getFilterSettings()
            val hasFilterSettings = if (filterSettings != null &&
                (filterSettings.salary.isNotEmpty() ||
                    filterSettings.onlyWithSalary ||
                    filterSettings.country.name.isNotEmpty() ||
                    filterSettings.region.name.isNotEmpty() ||
                    filterSettings.industry.name.isNotEmpty())) {
                true
            } else {
                false
            }
            if (filter != null &&
                (filter.salary.isNotEmpty() ||
                    filter.onlyWithSalary ||
                    filter.country.name.isNotEmpty() ||
                    filter.region.name.isNotEmpty() ||
                    filter.industry.name.isNotEmpty())) {
                stateLiveData.postValue(FilterStates.HasFilters(
                    filter.salary,
                    filter.onlyWithSalary,
                    filter.industry,
                    filter.country,
                    filter.region,
                    hasFilterSettings
                ))
            } else {
                stateLiveData.postValue(FilterStates.HasFilters(
                    "",
                    false,
                    Industry(
                        "",
                        "",
                        false
                    ),
                    Country(
                        "",
                        ""
                    ),
                    Region(
                        "",
                        "",
                        "",
                        ""
                    ),
                    hasFilterSettings
                ))
            }
        }
    }

    fun setFilterSettings(salary: String, onlyWithSalary: Boolean){
        viewModelScope.launch {
            filterInteractor.setFilterSettings(salary, onlyWithSalary)
            stateLiveData.postValue(FilterStates.SaveSettings)
        }
    }

    fun clearFilter(){
        viewModelScope.launch {
            filterInteractor.clearFilter()
            stateLiveData.postValue(FilterStates.ClearSettings)
        }
    }

    fun deleteCountryAndRegionFilter() {
        viewModelScope.launch {
            filterInteractor.deleteRegionFilter()
            filterInteractor.deleteCountryFilter()
            stateLiveData.postValue(FilterStates.DeleteCountryAndRegion)
        }
    }

    fun deleteIndustryFilter() {
        viewModelScope.launch {
            try {
                filterInteractor.deleteIndustryFilter()
                stateLiveData.postValue(FilterStates.DeleteIndustry)
            } catch (e: Exception) {
            }
        }
    }

}
