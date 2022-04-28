package com.example.navigationunittesting.di.viewmodel

import android.app.Application
import android.app.DatePickerDialog
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigationunittesting.database.RegistrationDAO
import com.example.navigationunittesting.model.RegistrationModel
import java.util.*
import java.util.regex.Pattern

class RegistrationViewModel(
    var mRegistrationDatabase: RegistrationDAO,
    var androidContext: Application
) : ViewModel() {

    var mEmailID = MutableLiveData<String>()
    var mDOB = MutableLiveData<String>()
    var mAddress = MutableLiveData<String>()
    var mName = MutableLiveData<String>()
    var mVisibility = MutableLiveData<Boolean>()


    fun dateTimePicker() {
        val mCalendar = Calendar.getInstance()
        val yearSelected = mCalendar.get(Calendar.YEAR)
        val month = mCalendar.get(Calendar.MONTH)
        val day = mCalendar.get(Calendar.DAY_OF_MONTH)
        val mDatePickerDialog = DatePickerDialog(
            androidContext,
            { _, year, monthOfYear, dayOfMonth ->
                mDOB.value = """$dayOfMonth - ${monthOfYear + 1} - $year"""
            }, yearSelected, month, day
        )
        mDatePickerDialog.show()
    }


    fun setOnClickListener() {

        if (mName.value.isNullOrEmpty()) {
            val mToast = Toast.makeText(androidContext, "enter Name", Toast.LENGTH_SHORT)
            mToast.setGravity(
                Gravity.CENTER_HORIZONTAL, 0, 0
            )
            mToast.show()
        } else
            if (mEmailID.value.isNullOrEmpty()) {
                val mToast = Toast.makeText(androidContext, "enter email ID", Toast.LENGTH_SHORT)
                mToast.setGravity(
                    Gravity.CENTER_HORIZONTAL, 0, 0
                )
                mToast.show()
            } else if (!isValidEmailID(mEmailID.value.toString())) {
                val mToast =
                    Toast.makeText(androidContext, "enter valid email ID", Toast.LENGTH_SHORT)
                mToast.setGravity(
                    Gravity.CENTER_HORIZONTAL, 0, 0
                )
                mToast.show()
            }  else if (mAddress.value.isNullOrEmpty()) {
                val mToast =
                    Toast.makeText(androidContext, "enter Address", Toast.LENGTH_SHORT)
                mToast.setGravity(
                    Gravity.CENTER_HORIZONTAL, 0, 0
                )
                mToast.show()
            } else {
                mVisibility.value = true
                val mRegistrationModel = RegistrationModel(
                    mName.value.toString(),
                    mAddress.value.toString(),
                    "",
                    mEmailID.value.toString()
                )
                mVisibility.value = false
                mName.value =""
                mAddress.value =""
                mEmailID.value=""

                insertData(mRegistrationModel)


            }

    }

    fun insertData(mRegistrationModel: RegistrationModel) {
        mRegistrationDatabase.insertData(mRegistrationModel)

    }


    fun isValidEmailID(email: String): Boolean {
        val emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$"
        val mPattern = Pattern.compile(emailRegex)
        return mPattern.matcher(email).matches();
    }

}