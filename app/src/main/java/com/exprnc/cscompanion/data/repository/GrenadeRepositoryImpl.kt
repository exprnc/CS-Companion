package com.exprnc.cscompanion.data.repository

import com.exprnc.cscompanion.data.local.dao.GrenadeDao
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.repository.GrenadeRepository
import javax.inject.Inject

class GrenadeRepositoryImpl @Inject constructor(
    private val grenadeDao: GrenadeDao
) : GrenadeRepository {
    override suspend fun getGrenadesByMapId(mapId: String): List<Grenade> {
        return grenadeDao.getGrenadesByMapId(mapId).map {
            Grenade(
                grenadeId = it.grenadeId,
                name = it.name,
                side = it.side,
                type = it.type,
                offsetX = it.offsetX,
                offsetY = it.offsetY,
                mapId = it.mapId
            )
        }
    }
}