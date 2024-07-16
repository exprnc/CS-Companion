package com.exprnc.cscompanion.domain.repository

import com.exprnc.cscompanion.data.local.entities.PositionDto
import com.exprnc.cscompanion.domain.model.Position

interface PositionRepository {
    suspend fun getPositionsByGrenadeId(grenadeId: String): List<Position>
    suspend fun insertPosition(position: Position)
}