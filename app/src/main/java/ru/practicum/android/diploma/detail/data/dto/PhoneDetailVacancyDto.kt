package ru.practicum.android.diploma.detail.data.dto

import com.google.gson.annotations.SerializedName

data class PhoneDetailVacancyDto(
    @SerializedName("city")
    val city: String,
    @SerializedName("comment")
    val comment: String?,
    @SerializedName("country")
    val country: String,
    @SerializedName("number")
    val number: String,
)
