package com.benjaminledet.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class DataResponse(

    @SerializedName("results")
    val results: List<VehicleResponse>
)

data class VehicleResponse(

    @SerializedName("id")
    val id: Long,

    @SerializedName("title")
    val title: String,

    @SerializedName("starting_price")
    val startingPrice: Double,

    @SerializedName("currency_used")
    val currencyUsed: String,

    @SerializedName("review_average")
    val reviewAverage: Double,

    @SerializedName("review_count")
    val reviewCount: Int,

    @SerializedName("vehicle_type")
    val vehicleType: String,

    @SerializedName("vehicle_beds")
    val vehicleBeds: Int,

    @SerializedName("vehicle_seats")
    val vehicleSeats: Int,

    @SerializedName("vehicle_location_city")
    val vehicleLocationCity: String,

    @SerializedName("vehicle_location_zipcode")
    val vehicleLocationZipCode: String,

    @SerializedName("vehicle_owner_first_name")
    val vehicleOwnerFirstName: String,

    @SerializedName("vehicle_owner_language")
    val vehicleOwnerLanguage: String,

    @SerializedName("vehicle_owner_picture_url")
    val vehicleOwnerPictureUrl: String?,

    @SerializedName("url")
    val url: String,

    @SerializedName("pictures")
    val pictures: List<PictureResponse>
)

data class PictureResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String
)