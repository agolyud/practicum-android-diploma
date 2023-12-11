package ru.practicum.android.diploma.detail.data.dto

import com.google.gson.annotations.SerializedName

data class DetailVacancyDto(
    @SerializedName("area")
    val areaDetailVacancyDto: AreaDetailVacancyDto,
    @SerializedName("employer")
    val employerDetailVacancyDto: EmployerDetailVacancyDto,
    @SerializedName("employment")
    val employmentDetailVacancyDto: EmploymentDetailVacancyDto,
    @SerializedName("experience")
    val experienceDetailVacancyDto: ExperienceDetailVacancyDto?,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("salary")
    val salaryDetailVacancyDto: SalaryDetailVacancyDto?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("key_skills")
    val keySkillDetailVacancyDtos: List<KeySkillDetailVacancyDto>?,
    @SerializedName("contacts")
    val contactsDetailVacancyDto: ContactsDetailVacancyDto?,
    @SerializedName("alternate_url")
    val url: String?
)
