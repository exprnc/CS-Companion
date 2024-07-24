package com.exprnc.cscompanion.data.mappers

import com.exprnc.cscompanion.data.local.entities.GrenadeDto
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.GrenadeType

class GrenadeMapper : Mapper<GrenadeDto, Grenade> {
    override fun map(from: GrenadeDto): Grenade {
        return Grenade(
            grenadeId = from.grenadeId,
            name = from.name,
            side = from.side,
            type = GrenadeType.valueOf(from.type),
            offsetX = from.offsetX,
            offsetY = from.offsetY,
            mapId = from.mapId
        )
    }
}