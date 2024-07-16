package com.exprnc.cscompanion.data.mappers

import com.exprnc.cscompanion.data.local.entities.GrenadeDto
import com.exprnc.cscompanion.data.local.entities.MapDto
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Map

class GrenadeMapper : Mapper<GrenadeDto, Grenade> {
    override fun map(from: GrenadeDto): Grenade =
        Grenade(
            grenadeId = from.mapId,
            name = from.name,
            side = from.side,
            type = from.type,
            offsetX = from.offsetX,
            offsetY = from.offsetY,
            mapId = from.mapId
        )
}