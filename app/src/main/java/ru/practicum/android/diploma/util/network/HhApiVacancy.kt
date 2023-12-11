package ru.practicum.android.diploma.util.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.detail.data.dto.DetailVacancyDto
import ru.practicum.android.diploma.search.data.models.SearchResponse

interface HhApiVacancy {

    @GET("vacancies")
    fun getVacancyList(
        @QueryMap options: HashMap<String, String>
    ): SearchResponse

    @GET("vacancies/{vacancy_id}")
    suspend fun getDetail(
        @Path("vacancy_id") vacancy: String
    ): DetailVacancyDto

    // Тут свои запросы

    /*companion object {
        const val HEADER_AUTH = "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
    }*/
}
