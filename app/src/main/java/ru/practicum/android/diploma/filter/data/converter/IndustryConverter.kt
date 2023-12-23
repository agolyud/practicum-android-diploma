package ru.practicum.android.diploma.filter.data.converter

import ru.practicum.android.diploma.filter.data.model.IndustryDto
import ru.practicum.android.diploma.filter.domain.models.Industry

object IndustryConverter {
    fun map(industry: IndustryDto): Industry {
        return Industry(
            id = industry.id!!,
            name = industry.name!!,
            isChecked = false
        )
    }

    fun map(industry: Industry): IndustryDto {
        return IndustryDto(
            id = industry.id,
            name = industry.name,
            industries = null
        )
    }
}
