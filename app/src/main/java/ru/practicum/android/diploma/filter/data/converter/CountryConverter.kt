package ru.practicum.android.diploma.filter.data.converter

import ru.practicum.android.diploma.filter.data.model.CountryDto
import ru.practicum.android.diploma.filter.domain.models.Region

object CountryConverter {
    fun map(area: CountryDto): Region {
        return Region(
            id = area.id,
            name = area.name
        )
    }

    fun map(area: Region): CountryDto {
        return CountryDto(
            id = area.id,
            name = area.name,
            areas = listOf(null)
        )
    }
}
