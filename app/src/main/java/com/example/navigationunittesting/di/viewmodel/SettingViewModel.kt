package com.example.navigationunittesting.di.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SettingViewModel() : ViewModel() {

     val mOnClick = MutableLiveData<Int>()

    fun setOnClickListener() {

        mOnClick.value = 100

    }

}