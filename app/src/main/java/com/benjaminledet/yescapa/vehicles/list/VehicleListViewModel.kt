package com.benjaminledet.yescapa.vehicles.list

import android.app.Application
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.benjaminledet.core.data.local.dao.VehicleDao
import com.benjaminledet.core.data.local.model.Vehicle
import com.benjaminledet.core.data.mediator.VehicleMediator
import kotlinx.coroutines.flow.Flow

class VehicleListViewModel @ViewModelInject constructor(application: Application, @Assisted private val savedStateHandle: SavedStateHandle, vehicleMediator: VehicleMediator, private val vehicleDao: VehicleDao): AndroidViewModel(application) {

   val vehicles: Flow<PagingData<Vehicle>> = Pager(PagingConfig(pageSize = 50), remoteMediator = vehicleMediator) {
      vehicleDao.getAll()
   }.flow.cachedIn(viewModelScope)

}