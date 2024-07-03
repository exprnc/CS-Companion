package com.exprnc.cscompanion.data.repository

import com.exprnc.cscompanion.data.local.dao.MapDao
import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.domain.repository.MapRepository
import javax.inject.Inject


class MapRepositoryImpl @Inject constructor(
    private val mapDao: MapDao
) : MapRepository {
    override suspend fun getMapsByActivePool(activePool: Boolean): List<Map> {
        return mapDao.getMapsByActivePool(activePool).map {
            Map(
                mapId = it.mapId,
                name = it.name,
                image = it.image,
                activePool = it.activePool,
                radarImage = it.radarImage,
                radarImageWithCallouts = it.radarImageWithCallouts
            )
        }
    }
}