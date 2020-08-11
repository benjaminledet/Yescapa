package com.benjaminledet.core.data.remote

import com.benjaminledet.core.data.local.model.Vehicle
import com.benjaminledet.core.data.remote.response.VehicleResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseClient @Inject constructor() {

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        // .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private val service = Retrofit.Builder()
        .baseUrl(FIREBASE_HOSTING_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FirebaseService::class.java)

    suspend fun getVehicles(): List<Vehicle> {
        val results = service.getVehiclesAsync().results
        return results.map { vehicleResponseToVehicle(it) }
    }

    private fun vehicleResponseToVehicle(vehicleResponse: VehicleResponse): Vehicle = Vehicle(
        id = vehicleResponse.id,
        title = vehicleResponse.title,
        owner = vehicleResponse.vehicleOwnerFirstName,
        ownerPictureUrl = vehicleResponse.vehicleOwnerPictureUrl,
        type = when (vehicleResponse.vehicleType) {
            TYPE_VAN -> Vehicle.Type.VAN
            else -> Vehicle.Type.COACH_BUILT
        },
        price = vehicleResponse.startingPrice,
        currency = vehicleResponse.currencyUsed,
        reviewAverage = vehicleResponse.reviewAverage,
        reviewCount = vehicleResponse.reviewCount,
        location = vehicleResponse.vehicleLocationCity,
        pictureUrls = vehicleResponse.pictures.map { it.url }
    )

    companion object {
        const val FIREBASE_HOSTING_URL = "https://yescapa-44d3c.firebaseapp.com"
        const val TIMEOUT = 30L
        const val TYPE_VAN = "Van"
        const val TYPE_COACH_BUILT = "CoachBuilt"
    }
}