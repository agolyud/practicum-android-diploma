package ru.practicum.android.diploma.detail.data.dto

import com.google.gson.annotations.SerializedName

data class AreaDetailVacancyDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)
