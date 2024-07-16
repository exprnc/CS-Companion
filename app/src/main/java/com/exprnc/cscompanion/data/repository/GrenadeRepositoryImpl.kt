package com.exprnc.cscompanion.data.repository

import com.exprnc.cscompanion.data.local.dao.GrenadeDao
import com.exprnc.cscompanion.data.local.entities.GrenadeDto
import com.exprnc.cscompanion.data.mappers.GrenadeMapper
import com.exprnc.cscompanion.domain.model.Grenade
import com.exprnc.cscompanion.domain.repository.GrenadeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GrenadeRepositoryImpl @Inject constructor(
    private val grenadeDao: GrenadeDao,
) : GrenadeRepository {

    private val mapper by lazy { GrenadeMapper() }

    override suspend fun getGrenadesByMapId(mapId: String) = withContext(Dispatchers.IO) {
        grenadeDao.getGrenadesByMapId(mapId).map { mapper.map(it) }
    }

    override suspend fun insertGrenade(grenade: Grenade) = withContext(Dispatchers.IO) {
        grenadeDao.insert(
            GrenadeDto(
                grenadeId = grenade.grenadeId,
                name = grenade.name,
                side = grenade.side,
                type = grenade.type,
                offsetX = grenade.offsetX,
                offsetY = grenade.offsetY,
                mapId = grenade.mapId
            )
        )
    }
}