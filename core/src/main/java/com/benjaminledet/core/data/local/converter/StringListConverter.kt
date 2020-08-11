package com.benjaminledet.core.data.local.converter

import androidx.room.TypeConverter

object StringListConverter {

    @TypeConverter
    fun fromList(data: List<String>?): String = data?.joinToString(separator = ",") ?: ""

    @TypeConverter
    fun toList(data: String?): List<String> = data?.split(",") ?: listOf()

}