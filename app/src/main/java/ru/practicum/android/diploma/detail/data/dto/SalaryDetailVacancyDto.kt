package ru.practicum.android.diploma.detail.data.dto

import com.google.gson.annotations.SerializedName

data class SalaryDetailVacancyDto(
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("from")
    val from: Int?,
    @SerializedName("gross")
    val gross: Boolean,
    @SerializedName("to")
    val to: Int?
)
