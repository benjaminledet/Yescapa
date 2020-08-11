package com.benjaminledet.core.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Vehicle.TABLE_NAME)
data class Vehicle(

    @PrimaryKey()
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = TITLE)
    val title: String,

    @ColumnInfo(name = OWNER)
    val owner: String,

    @ColumnInfo(name = OWNER_PICTURE_URL)
    val ownerPictureUrl: String?,

    val type: Type,

    @ColumnInfo(name = PRICE)
    val price: Double,

    @ColumnInfo(name = CURRENCY)
    val currency: String,

    @ColumnInfo(name = REVIEW_AVERAGE)
    val reviewAverage: Double,

    @ColumnInfo(name = REVIEW_COUNT)
    val reviewCount: Int,

    @ColumnInfo(name = LOCATION)
    val location: String,

    @ColumnInfo(name = PICTURE_URLS)
    val pictureUrls: List<String>
) {

    enum class Type {
        COACH_BUILT, VAN
    }

    /**
     * Transform the review average to a list of Int representing the rating state:
     * 1 for a full star
     * 0 for a half star
     * -1 for an empty star
     */
    fun reviewAverageToIntList(): List<Int> {
        // there can be only one half star
        var isHalfStarAsBeenSet = false
        return Array(5) {
            when {
                it + 1 <= reviewAverage -> 1
                else -> {
                    if (!isHalfStarAsBeenSet && reviewAverage % 1.0 != 0.0) {
                        isHalfStarAsBeenSet = true
                        0
                    } else {
                        -1
                    }
                }
            }
        }.toList()
    }

    companion object {

        const val TABLE_NAME = "Vehicle"
        const val ID = "id"
        const val TITLE = "title"
        const val OWNER = "owner"
        const val OWNER_PICTURE_URL = "ownerPictureUrl"
        const val PRICE = "price"
        const val CURRENCY = "currency"
        const val REVIEW_AVERAGE = "reviewAverage"
        const val REVIEW_COUNT = "reviewCount"
        const val LOCATION = "location"
        const val PICTURE_URLS = "pictureUrls"
    }
}