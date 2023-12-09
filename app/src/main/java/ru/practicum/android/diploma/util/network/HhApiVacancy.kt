package ru.practicum.android.diploma.util.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.search.data.models.SearchResponse


interface HhApiVacancy {

    @Headers(
        HEADER_AUTH,
        HH_USER
    )
    @GET("vacancies")
     fun getVacancyList(
        @QueryMap options: HashMap<String, String>
    ): SearchResponse

    //Тут свои запросы

    companion object{
        const val HEADER_AUTH = "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
        const val HH_USER = "HH-User-Agent: EmployMe (asa-tek68@mail.ru)"
    }
}
