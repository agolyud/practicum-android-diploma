package ru.practicum.android.diploma.filter.domain.models

import ru.practicum.android.diploma.search.data.models.Industry

data class FilterSettings(
    val salary: String?,
    val onlyWithSalary: Boolean,
    val industries: ArrayList<Industry>,
    val country: String?,
    val area: Region
)
