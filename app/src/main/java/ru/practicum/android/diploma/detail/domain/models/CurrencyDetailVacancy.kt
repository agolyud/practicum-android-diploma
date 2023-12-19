package ru.practicum.android.diploma.detail.domain.models

enum class CurrencyDetailVacancy(val symbol: String) {
    RUR("₽"),
    BYR("Br"),
    USD("$"),
    EUR("€"),
    KZT("₸"),
    UAH("₴"),
    AZN("₼"),
    UZS("som"),
    GEL("₾"),
    KGT("с"),
    NONE("");

    companion object {
        fun getCurrency(value: String): CurrencyDetailVacancy {
            return when (value) {
                "RUR", "RUB" -> RUR
                "BYR" -> BYR
                "USD" -> USD
                "EUR" -> EUR
                "KZT" -> KZT
                "UAH" -> UAH
                "AZN" -> AZN
                "UZS" -> UZS
                "GEL" -> GEL
                "KGT" -> KGT
                else -> NONE
            }
        }
    }
}
