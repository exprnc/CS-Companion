package com.exprnc.cscompanion.data.mappers

import com.exprnc.cscompanion.data.local.entities.MapDto
import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.domain.model.MapType

class MapMapper : Mapper<MapDto, Map> {
    override fun map(from: MapDto): Map =
        Map(
            mapId = from.mapId,
            name = from.name,
            type = MapType.valueOf(from.type),
            activePool = from.activePool,
            icon = from.icon,
            image = from.image,
            radarImage = from.radarImage,
            radarImageWithCallouts = from.radarImageWithCallouts
        )
}