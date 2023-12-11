package ru.practicum.android.diploma.search.data.models

private const val NO_CONNECTION_CODE = 500
private const val SUCCESS_CODE = 500
private const val ERROR_CODE = 500

enum class ResponseCodes(val code: Int) {
    NO_NET_CONNECTION(NO_CONNECTION_CODE),
    SUCCESS(SUCCESS_CODE),
    ERROR(ERROR_CODE)
}
