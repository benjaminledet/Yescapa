package com.benjaminledet.core.data.local.converter

import androidx.room.TypeConverter
import com.benjaminledet.core.data.local.model.Vehicle

object VehicleTypeConverter {

    @TypeConverter
    fun fromCreditType(data: Vehicle.Type?): String = data?.name ?: Vehicle.Type.COACH_BUILT.name

    @TypeConverter
    fun toCreditType(data: String?): Vehicle.Type = Vehicle.Type.valueOf(data ?: Vehicle.Type.COACH_BUILT.name)

}