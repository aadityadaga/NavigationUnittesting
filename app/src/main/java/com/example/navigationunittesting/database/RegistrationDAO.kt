package com.example.navigationunittesting.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.navigationunittesting.model.RegistrationModel

@Dao
interface RegistrationDAO {

    @Insert
    fun insertData(mRegistrationModel: RegistrationModel)

    @Query("select * from Registration")
    fun getAllData(): List<RegistrationModel?>

    @Delete
    fun deleteRow(mRegistrationModel: RegistrationModel)


}