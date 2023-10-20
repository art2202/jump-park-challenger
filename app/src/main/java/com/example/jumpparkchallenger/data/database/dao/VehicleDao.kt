package com.example.jumpparkchallenger.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.jumpparkchallenger.data.database.entity.VehicleEntity

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertVehicle(vehicle: VehicleEntity)

    @Delete
     fun deleteVehicle(vehicle: VehicleEntity)

    @Query("SELECT * FROM vehicle")
     fun getAllVehicles(): List<VehicleEntity>

    @Query("SELECT * FROM vehicle WHERE plate = :plate")
     fun getVehicleByPlate(plate: String): VehicleEntity?

    @Query("DELETE FROM vehicle")
     fun deleteAllVehicles()
}