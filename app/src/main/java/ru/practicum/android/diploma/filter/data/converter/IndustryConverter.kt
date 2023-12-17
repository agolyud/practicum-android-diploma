package ru.practicum.android.diploma.filter.data.converter

import ru.practicum.android.diploma.filter.data.model.IndustryDto
import ru.practicum.android.diploma.search.data.models.Industry

object IndustryConverter {
    fun mapToDto(industry: IndustryDto): Industry {
        return Industry(
            id = industry.id,
            name = industry.name
        )
    }

    fun map(industry: Industry): IndustryDto {
        return IndustryDto(
            id = industry.id,
            name = industry.name,
            industries = null
        )
    }

    fun mapFromDto(industries: ArrayList<IndustryDto>): ArrayList<Industry>{
        val _industries = arrayListOf<Industry>()

        industries.forEach{
            _industries.add(mapToDto(it))
        }

        return _industries
    }

    fun mapToDto(industries: ArrayList<Industry>): ArrayList<IndustryDto>{
        val _industries = arrayListOf<IndustryDto>()

        industries.forEach{
            _industries.add(map(it))
        }

        return _industries
    }
}
