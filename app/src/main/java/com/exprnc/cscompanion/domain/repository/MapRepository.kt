package com.exprnc.cscompanion.domain.repository

import com.exprnc.cscompanion.domain.model.Map
import com.exprnc.cscompanion.domain.model.MapType

interface MapRepository {
    suspend fun getMapsByActivePool(activePool: Boolean): List<Map>
    suspend fun getMapsByType(type: MapType): List<Map>
    suspend fun insertMap(map: Map)
}