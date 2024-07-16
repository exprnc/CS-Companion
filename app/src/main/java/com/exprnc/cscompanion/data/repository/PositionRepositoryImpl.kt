package com.exprnc.cscompanion.data.repository

import com.exprnc.cscompanion.data.local.dao.PositionDao
import com.exprnc.cscompanion.data.local.entities.PositionDto
import com.exprnc.cscompanion.data.mappers.GrenadeMapper
import com.exprnc.cscompanion.data.mappers.PositionMapper
import com.exprnc.cscompanion.domain.model.Position
import com.exprnc.cscompanion.domain.repository.PositionRepository
import javax.inject.Inject

class PositionRepositoryImpl @Inject constructor(
    private val positionDao: PositionDao,
) : PositionRepository {

    private val mapper by lazy { PositionMapper() }

    override suspend fun getPositionsByGrenadeId(grenadeId: String): List<Position> {
        return positionDao.getPositionsByGrenadeId(grenadeId).map { mapper.map(it) }
    }

    override suspend fun insertPosition(position: Position) {
        positionDao.insert(
            PositionDto(
                positionId = position.positionId,
                videoURL = position.videoURL,
                offsetX = position.offsetX,
                offsetY = position.offsetY,
                grenadeId = position.grenadeId,
            )
        )
    }
}