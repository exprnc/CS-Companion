package com.exprnc.cscompanion.domain.repository

import com.exprnc.cscompanion.data.local.entities.PositionDto
import com.exprnc.cscompanion.domain.model.Position

interface PositionRepository {
    suspend fun getPositionsByMapId(mapId: String): List<Position>
    suspend fun insertPosition(position: Position)
}