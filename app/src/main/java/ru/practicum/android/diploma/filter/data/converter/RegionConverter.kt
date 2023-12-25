package ru.practicum.android.diploma.filter.data.converter

import ru.practicum.android.diploma.filter.data.model.RegionDto
import ru.practicum.android.diploma.filter.domain.models.Region

object RegionConverter {
    fun map(region: RegionDto?): Region {
        return Region(
            id = region?.id!!,
            name = region?.name!!
        )
    }

    fun map(area: Region): RegionDto {
        return RegionDto(
            id = area.id,
            name = area.name,
            areas = null
        )
    }

    fun mapDtoToRegions(regionDto: RegionDto?): List<Region> {
        val regions = mutableListOf<Region>()

        if (regionDto != null) {
            regions.add(
                Region(
                    id = regionDto.id.toString(),
                    name = regionDto.name.toString()
                )
            )
            if (regionDto.areas?.isNotEmpty() == true){
                regions.addAll(mapDtosToRegions(regionDto.areas))
            }
        }

        return regions
    }

    fun mapDtosToRegions(regionDtos: List<RegionDto>?): List<Region> {
        val regions = mutableListOf<Region>()

        regionDtos?.forEach {
            regions.addAll(mapDtoToRegions(it))
        }

        return regions

    }
}
