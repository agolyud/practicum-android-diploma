package ru.practicum.android.diploma.util.network

import ru.practicum.android.diploma.BuildConfig

interface HhApiVacancy {

    // Тут свои запросы

    companion object {
        const val HEADER_AUTH = "Authorization: Bearer ${BuildConfig.HH_ACCESS_TOKEN}"
        const val HH_USER = "HH-User-Agent: EmployMe (asa-tek68@mail.ru)"
    }
}
