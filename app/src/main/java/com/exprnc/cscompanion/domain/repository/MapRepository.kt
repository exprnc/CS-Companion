package com.exprnc.cscompanion.domain.repository

import com.exprnc.cscompanion.domain.model.Map

interface MapRepository {
    suspend fun getMapsByActivePool(activePool: Boolean): List<Map>
}