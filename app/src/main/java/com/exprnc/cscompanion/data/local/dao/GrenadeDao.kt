package com.exprnc.cscompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.exprnc.cscompanion.data.local.entities.GrenadeDto

@Dao
interface GrenadeDao {
    @Query("SELECT * FROM grenades WHERE map_id = (:mapId)")
    fun getGrenadesByMapId(mapId: String): List<GrenadeDto>
}