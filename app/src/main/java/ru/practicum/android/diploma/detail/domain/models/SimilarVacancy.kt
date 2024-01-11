package ru.practicum.android.diploma.detail.domain.models

data class SimilarVacancy(
    val id: String?,
    val name: String?,
    val address: String?,
    val salary: SalaryDetailVacancy?,
    val idEmployer: String?,
    val logoUrlsEmployerOriginal: String?,
    val nameEmployer: String?,
)
