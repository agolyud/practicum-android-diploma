package ru.practicum.android.diploma.detail.data.dto

import com.google.gson.annotations.SerializedName

data class EmployerDetailVacancyDto(
    @SerializedName("alternate_url")
    val alternateUrl: String,
    @SerializedName("blacklisted")
    val blacklisted: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("logo_urls")
    val logoUrlsDetailVacancyDto: LogoUrlsDetailVacancyDto?,
    @SerializedName("name")
    val name: String,
    @SerializedName("trusted")
    val trusted: Boolean,
    @SerializedName("url")
    val url: String
)
