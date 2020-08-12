package com.benjaminledet.core

import com.benjaminledet.core.data.local.model.Vehicle
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class VehicleTest {

    private lateinit var vehicle: Vehicle

    @Before
    fun setupVehicle() {
        vehicle = Vehicle(
            id = 1,
            title = "",
            owner = "",
            ownerPictureUrl = "",
            type = Vehicle.Type.VAN,
            price = 0.0,
            currency = "",
            reviewAverage = 0.0,
            reviewCount = 1,
            location = "",
            pictureUrls = listOf()
        )
    }

    @Test
    fun averageToList() {
        Assert.assertEquals(listOf(1,1,1,-1,-1), vehicle.copy(reviewAverage = 3.0).reviewAverageToIntList())
    }

    @Test
    fun averageWithDecimalToList() {
        Assert.assertEquals(listOf(1,1,1,1,0), vehicle.copy(reviewAverage = 4.5).reviewAverageToIntList())
    }

}