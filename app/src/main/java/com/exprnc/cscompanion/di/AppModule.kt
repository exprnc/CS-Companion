package com.exprnc.cscompanion.di

import android.content.Context
import androidx.room.Room
import com.exprnc.cscompanion.data.local.AppDatabase
import com.exprnc.cscompanion.data.local.dao.GrenadeDao
import com.exprnc.cscompanion.data.local.dao.MapDao
import com.exprnc.cscompanion.data.local.dao.PositionDao
import com.exprnc.cscompanion.data.repository.GrenadeRepositoryImpl
import com.exprnc.cscompanion.data.repository.MapRepositoryImpl
import com.exprnc.cscompanion.data.repository.PositionRepositoryImpl
import com.exprnc.cscompanion.domain.repository.GrenadeRepository
import com.exprnc.cscompanion.domain.repository.MapRepository
import com.exprnc.cscompanion.domain.repository.PositionRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "cs_companion_database"
        ).build()
    }

    @Provides
    fun provideMapDao(appDatabase: AppDatabase): MapDao {
        return appDatabase.mapDao()
    }

    @Provides
    fun provideGrenadeDao(appDatabase: AppDatabase): GrenadeDao {
        return appDatabase.grenadeDao()
    }

    @Provides
    fun providePositionDao(appDatabase: AppDatabase): PositionDao {
        return appDatabase.positionDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMapRepository(
        mapRepositoryImpl: MapRepositoryImpl,
    ): MapRepository

    @Binds
    abstract fun bindGrenadeRepository(
        grenadeRepositoryImpl: GrenadeRepositoryImpl,
    ): GrenadeRepository

    @Binds
    abstract fun bindPositionRepository(
        positionRepositoryImpl: PositionRepositoryImpl,
    ): PositionRepository
}