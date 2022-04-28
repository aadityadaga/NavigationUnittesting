package com.example.navigationunittesting.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Registration")
data class RegistrationModel(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "address")
    var address: String,
    @ColumnInfo(name = "dob")
    var dob: String,
    @PrimaryKey
    @ColumnInfo(name = "emailID")
    var emailID: String
): Parcelable
