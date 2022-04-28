package com.example.navigationunittesting.di.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationunittesting.database.RegistrationDAO
import com.example.navigationunittesting.model.RegistrationModel

class DataDisplayViewModel(
    var mRegistrationDatabase: RegistrationDAO
) : ViewModel() {


    var mRegistrationModelList = MutableLiveData<List<RegistrationModel?>?>()

    fun getData(): LiveData<List<RegistrationModel?>?> {
        mRegistrationModelList.value = mRegistrationDatabase.getAllData()
        return mRegistrationModelList
    }


    fun deleteData(model: RegistrationModel) {
        mRegistrationDatabase.deleteRow(model)

    }


}