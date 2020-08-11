package com.benjaminledet.core.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.benjaminledet.core.data.local.YescapaDatabase
import com.benjaminledet.core.data.local.dao.VehicleDao
import com.benjaminledet.core.data.local.model.Vehicle
import com.benjaminledet.core.data.remote.FirebaseClient
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class VehicleMediator @Inject constructor(private val firebaseClient: FirebaseClient, private val database: YescapaDatabase, private val vehicleDao: VehicleDao) : RemoteMediator<Int, Vehicle>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Vehicle>): MediatorResult {
        val loadKey = when (loadType) {
            LoadType.REFRESH -> null
            // In this example, you never need to prepend, since REFRESH will always load the first page in the list. Immediately return, reporting end of pagination.
            LoadType.PREPEND ->
                return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                /* if the service provides pagination whe should do :
                    val lastItem = state.lastItemOrNull()
                    // You must explicitly check if the last item is null when appending, since passing null to networkService is only valid for initial load. If lastItem is null it means no items were loaded after the initial REFRESH and there are no more items to load.
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    lastItem.id
                   */
                // but it doesn't so we just return a success
                return MediatorResult.Success(endOfPaginationReached = true)
            }
        }

        return try {
            // here we should implement pagination if the service permits it
            val response = firebaseClient.getVehicles(/*loadKey*/)
            database.withTransaction {
                // here instead of deleting all vehicles (which is the simple way) we could make a diff, like deleting only those which are not in the response anymore...
                if (loadType == LoadType.REFRESH) {
                    vehicleDao.deleteAll()
                }
                // Insert new vehicles into database, which invalidates the current PagingData, allowing Paging to present the updates in the DB.
                vehicleDao.insert(response)
            }

            MediatorResult.Success(endOfPaginationReached = /*response.nextKey == null*/ true)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}