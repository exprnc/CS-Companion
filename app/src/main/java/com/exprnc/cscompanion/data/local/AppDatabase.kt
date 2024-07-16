package com.exprnc.cscompanion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exprnc.cscompanion.data.local.dao.MapDao
import com.exprnc.cscompanion.data.local.dao.GrenadeDao
import com.exprnc.cscompanion.data.local.dao.PositionDao
import com.exprnc.cscompanion.data.local.entities.MapDto
import com.exprnc.cscompanion.data.local.entities.GrenadeDto
import com.exprnc.cscompanion.data.local.entities.PositionDto

@Database(entities = [MapDto::class, GrenadeDto::class, PositionDto::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mapDao(): MapDao
    abstract fun grenadeDao(): GrenadeDao
    abstract fun positionDao(): PositionDao
}