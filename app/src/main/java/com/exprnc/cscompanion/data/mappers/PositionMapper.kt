package com.exprnc.cscompanion.data.mappers

import com.exprnc.cscompanion.data.local.entities.GrenadeDto
import com.exprnc.cscompanion.data.local.entities.PositionDto
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.model.Position

class PositionMapper : Mapper<PositionDto, Position> {
    override fun map(from: PositionDto): Position =
        Position(
            positionId = from.positionId,
            videoURL = from.videoURL,
            offsetX = from.offsetX,
            offsetY = from.offsetY,
            grenadeId = from.grenadeId,
            mapId = from.mapId
        )
}