package com.exprnc.cscompanion.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.exprnc.cscompanion.data.local.entities.MapDto

@Dao
interface MapDao {
    @Query("SELECT * FROM maps WHERE active_pool = (:activePool)")
    fun getMapsByActivePool(activePool: Boolean): List<MapDto>

    @Query("SELECT * FROM maps WHERE type = (:type)")
    fun getMapsByType(type: String): List<MapDto>

    @Insert
    fun insert(map: MapDto)
}