package com.exprnc.cscompanion.data.repository

import com.exprnc.cscompanion.data.local.dao.MapDao
import com.exprnc.cscompanion.data.local.entities.MapDto
import com.exprnc.cscompanion.data.mappers.MapMapper
import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.domain.repository.MapRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MapRepositoryImpl @Inject constructor(
    private val mapDao: MapDao
) : MapRepository {

    private val mapper by lazy { MapMapper() }

    override suspend fun getMapsByActivePool(activePool: Boolean) = withContext(Dispatchers.IO) {
         mapDao.getMapsByActivePool(activePool).map { mapper.map(it) }
    }

    override suspend fun getMapsByType(type: String) = withContext(Dispatchers.IO) {
        mapDao.getMapsByType(type).map { mapper.map(it) }
    }

    override suspend fun insertMap(map: Map) = withContext(Dispatchers.IO) {
        mapDao.insert(
            MapDto(
                mapId = map.mapId,
                name = map.name,
                type = map.type.name,
                activePool = map.activePool,
                icon = map.icon,
                image = map.image,
                radarImage = map.radarImage,
                radarImageWithCallouts = map.radarImageWithCallouts,
            )
        )
    }
}