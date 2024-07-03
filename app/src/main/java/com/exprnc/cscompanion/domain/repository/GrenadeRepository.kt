package com.exprnc.cscompanion.domain.repository

import com.exprnc.cscompanion.domain.model.Grenade

interface GrenadeRepository {
    suspend fun getGrenadesByMapId(mapId: String): List<Grenade>
}