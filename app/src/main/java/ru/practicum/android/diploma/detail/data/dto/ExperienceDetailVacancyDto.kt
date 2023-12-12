package ru.practicum.android.diploma.detail.data.dto

import com.google.gson.annotations.SerializedName

data class ExperienceDetailVacancyDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
