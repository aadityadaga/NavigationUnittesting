package com.example.navigationunittesting.di.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationunittesting.model.RegistrationModel

class FullDataViewModel : ViewModel() {

    var mRegistrationModel = MutableLiveData<RegistrationModel>()
    val mOnClick = MutableLiveData<Int>()



    fun setOnClickListener(){

        mOnClick.value = 100

    }
}