package ru.practicum.android.diploma.detail.domain.models

data class DetailVacancy(
    val id: String,
    val name: String,
    val idEmployment: String?,
    val nameEmployment: String?,
    val alternateUrlEmployer: String?,
    val blacklistedEmployer: Boolean?,
    val idEmployer: String?,
    val logoUrlsEmployerOriginal: String?,
    val nameEmployer: String?,
    val trustedEmployer: Boolean?,
    val urlEmployer: String?,
    val address: String?,
    val idExperience: String?,
    val nameExperience: String?,
    val salary: SalaryDetailVacancy?,
    val description: String?,
    val keySkills: String?,
    val phone: String?,
    val contactName: String?,
    val email: String?,
    val comment: String?,
    val url: String?
)
