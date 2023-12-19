package ru.practicum.android.diploma.filter.data.converter

import ru.practicum.android.diploma.filter.data.model.RegionDto
import ru.practicum.android.diploma.filter.domain.models.Region

object AreaConverter {
    fun map(area: RegionDto): Region {
        return Region(
            id = area.id,
            name = area.name
        )
    }

    fun map(area: Region): RegionDto {
        return RegionDto(
            id = area.id,
            name = area.name,
            areas = null
        )
    }
}
