package com.benjaminledet.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.benjaminledet.core.data.local.converter.StringListConverter
import com.benjaminledet.core.data.local.converter.VehicleTypeConverter
import com.benjaminledet.core.data.local.dao.VehicleDao
import com.benjaminledet.core.data.local.model.Vehicle

@Database(entities = [Vehicle::class], version = YescapaDatabase.VERSION, exportSchema = true)
@TypeConverters(StringListConverter::class, VehicleTypeConverter::class)
abstract class YescapaDatabase : RoomDatabase() {

    abstract fun vehicleDao(): VehicleDao

    companion object {
        const val DATABASE_NAME = "YescapaDatabase"
        const val VERSION = 1
    }
}