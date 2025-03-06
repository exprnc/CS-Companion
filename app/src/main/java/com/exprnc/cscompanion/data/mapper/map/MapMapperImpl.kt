package com.exprnc.cscompanion.data.mapper.map

import com.exprnc.cscompanion.data.local.entity.map.MapEntity
import com.exprnc.cscompanion.data.remote.dto.map.MapDto
import com.exprnc.cscompanion.domain.model.map.Map
import com.exprnc.cscompanion.domain.model.map.MapType
import javax.inject.Inject

class MapMapperImpl @Inject constructor() : MapMapper {
    override fun fromDto(dto: MapDto): Map =
        Map(
            id = dto.id,
            name = dto.name,
            type = MapType.valueOf(dto.type),
            activePool = dto.activePool,
            icon = dto.iconUrl,
            image = dto.imageUrl,
            radarImage = dto.radarImageUrl,
            radarImageWithCallouts = dto.radarImageUrlWithCallouts,
            isCached = false,
            createdAt = dto.createdAt,
            updatedAt = dto.updatedAt
        )

    override fun fromEntity(entity: MapEntity): Map =
        Map()

    override fun toEntity(model: Map): MapEntity {
        TODO("Not yet implemented")
    }

}