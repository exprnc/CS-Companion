package com.exprnc.cscompanion.data.repository

import com.exprnc.cscompanion.data.local.dao.PositionDao
import com.exprnc.cscompanion.data.local.entities.PositionDto
import com.exprnc.cscompanion.data.mappers.GrenadeMapper
import com.exprnc.cscompanion.data.mappers.PositionMapper
import com.exprnc.cscompanion.domain.model.Position
import com.exprnc.cscompanion.domain.repository.PositionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PositionRepositoryImpl @Inject constructor(
    private val positionDao: PositionDao,
) : PositionRepository {

    private val mapper by lazy { PositionMapper() }

    override suspend fun getPositionsByMapId(mapId: String) = withContext(Dispatchers.IO) {
        positionDao.getPositionsByMapId(mapId).map { mapper.map(it) }
    }

    override suspend fun insertPosition(position: Position) = withContext(Dispatchers.IO) {
        positionDao.insert(
            PositionDto(
                positionId = position.positionId,
                videoURL = position.videoURL,
                offsetX = position.offsetX,
                offsetY = position.offsetY,
                grenadeId = position.grenadeId,
                mapId = position.mapId
            )
        )
    }
}