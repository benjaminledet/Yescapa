package com.benjaminledet.core.di

import android.content.Context
import androidx.room.Room
import com.benjaminledet.core.data.local.YescapaDatabase
import com.benjaminledet.core.data.local.dao.VehicleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule  {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): YescapaDatabase = Room.databaseBuilder(context, YescapaDatabase::class.java, YescapaDatabase.DATABASE_NAME).build()

    @Provides
    fun provideVehicleDao(database: YescapaDatabase): VehicleDao = database.vehicleDao()
}