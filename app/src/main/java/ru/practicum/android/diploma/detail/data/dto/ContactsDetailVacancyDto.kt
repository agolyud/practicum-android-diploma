package ru.practicum.android.diploma.detail.data.dto

import com.google.gson.annotations.SerializedName

data class ContactsDetailVacancyDto(
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phones")
    val phoneDetailVacancyDtos: List<PhoneDetailVacancyDto>?
)
