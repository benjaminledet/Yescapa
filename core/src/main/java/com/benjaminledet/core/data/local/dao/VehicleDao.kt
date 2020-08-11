package com.benjaminledet.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.benjaminledet.core.data.local.model.Vehicle
import kotlinx.coroutines.flow.Flow

@Dao
abstract class VehicleDao {

    @Delete
    abstract suspend fun delete(data: List<Vehicle>)

    @Query("DELETE FROM ${Vehicle.TABLE_NAME}")
    abstract suspend fun deleteAll(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(data: List<Vehicle>): List<Long>

    @Update
    abstract suspend fun update(data: List<Vehicle>)

    @Query("SELECT * FROM ${Vehicle.TABLE_NAME}")
    abstract fun getAll(): PagingSource<Int, Vehicle>

    @Query("SELECT * from ${Vehicle.TABLE_NAME} WHERE ${Vehicle.ID} = :id")
    abstract fun getByIdObservable(id: Long?): Flow<Vehicle?>

}