package ru.practicum.android.diploma.util.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.search.data.models.Response
import ru.practicum.android.diploma.search.data.models.ResponseCodes

class RetrofitNetworkClient(
    private val validator: InternetConnectionValidator
) : NetworkClient {

    override var lock = Any()

    private val retrofitHh =
        Retrofit.Builder()
            .baseUrl(BASE_HH_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val hhService = retrofitHh.create(HhApiVacancy::class.java)

    override suspend fun doRequest(dto: Any): Response {
        if (!validator.isConnected()) return Response()

        return Response().apply { resultCode = ResponseCodes.SUCCESS }
    }

    companion object {
        const val BASE_HH_API = "https://hh.ru/"
    }
}
