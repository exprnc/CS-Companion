package com.exprnc.cscompanion.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.exprnc.cscompanion.data.local.converter.LocalDateTimeConverter
import com.exprnc.cscompanion.data.local.converter.UUIDConverter
import com.exprnc.cscompanion.data.local.entity.map.MapEntity

@Database(entities = [MapEntity::class], version = 1)
@TypeConverters(LocalDateTimeConverter::class, UUIDConverter::class)
abstract class AppDatabase : RoomDatabase() {

}