package com.example.navigationunittesting.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.navigationunittesting.model.RegistrationModel


@Database(entities = [RegistrationModel::class], version = 1, exportSchema = false)
abstract class RegistrationDatabase : RoomDatabase() {


    abstract fun registrationDao(): RegistrationDAO




}