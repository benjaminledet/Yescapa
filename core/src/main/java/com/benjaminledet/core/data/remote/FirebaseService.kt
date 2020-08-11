package com.benjaminledet.core.data.remote

import com.benjaminledet.core.data.remote.response.DataResponse
import retrofit2.http.POST

interface FirebaseService {

    @POST("data.json")
    suspend fun getVehiclesAsync(): DataResponse

}