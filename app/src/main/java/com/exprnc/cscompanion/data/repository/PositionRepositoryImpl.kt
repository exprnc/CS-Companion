package com.exprnc.cscompanion.data.repository

import com.exprnc.cscompanion.data.local.dao.PositionDao
import com.exprnc.cscompanion.domain.model.Position
import com.exprnc.cscompanion.domain.repository.PositionRepository
import javax.inject.Inject

class PositionRepositoryImpl @Inject constructor(
    private val positionDao: PositionDao
) : PositionRepository {
    override suspend fun getPositionsByGrenadeId(grenadeId: String): List<Position> {
        return positionDao.getPositionsByGrenadeId(grenadeId).map {
            Position(
                positionId = it.positionId,
                videoURL = it.videoURL,
                offsetX = it.offsetX,
                offsetY = it.offsetY,
                grenadeId = it.grenadeId
            )
        }
    }
}