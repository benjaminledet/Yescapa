package com.benjaminledet.yescapa.vehicles.detail

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import com.benjaminledet.core.data.local.dao.VehicleDao
import com.benjaminledet.core.data.local.model.Vehicle
import kotlinx.coroutines.flow.Flow

class VehicleDetailViewModel @ViewModelInject constructor(application: Application, @Assisted private val savedStateHandle: SavedStateHandle, vehicleDao: VehicleDao): AndroidViewModel(application) {

    val vehicle: Flow<Vehicle?> = vehicleDao.getByIdObservable(savedStateHandle["vehicleId"])
}