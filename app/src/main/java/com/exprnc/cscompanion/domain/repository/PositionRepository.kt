package com.exprnc.cscompanion.domain.repository

import com.exprnc.cscompanion.domain.model.Position

interface PositionRepository {
    suspend fun getPositionsByGrenadeId(grenadeId: String): List<Position>
}