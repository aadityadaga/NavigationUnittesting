package com.example.navigationunittesting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.navigationunittesting.databinding.ActivityFullDataDisplayBinding
import com.example.navigationunittesting.di.viewmodel.FullDataViewModel
import com.example.navigationunittesting.model.RegistrationModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject

class FullDataDisplayActivity : AppCompatActivity() {

    private var mRegistrationModel: RegistrationModel? = null
    lateinit var mActivityFullDataDisplayBinding: ActivityFullDataDisplayBinding

    private val mFullDataViewModel by inject<FullDataViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mActivityFullDataDisplayBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_full_data_display)
        mRegistrationModel = intent.extras?.getParcelable<RegistrationModel>("registrationModel")
        mFullDataViewModel.mRegistrationModel.value = mRegistrationModel
        mActivityFullDataDisplayBinding.fullDataViewModel = mFullDataViewModel
        mFullDataViewModel.mOnClick.observe(this) {
            if (it == 100) {
                dialogDisplay()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(0, null)
        finish()
    }


    private fun dialogDisplay() {
        val mAlertDialogBuilder =
            MaterialAlertDialogBuilder(this@FullDataDisplayActivity, R.style.CutShapeTheme)
        mAlertDialogBuilder.setMessage(getString(R.string.delete_the_item))
        mAlertDialogBuilder.setPositiveButton("Ok") { dialogInterface, _ ->
            dialogInterface?.cancel()
            val mIntent = Intent()
            mIntent.putExtra("registrationModel", mRegistrationModel)
            setResult(100, mIntent)
            finish()
        }
        mAlertDialogBuilder.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface?.cancel()
        }
        val mAlertDialog = mAlertDialogBuilder.create()
        mAlertDialog.setCancelable(false)
        mAlertDialog.show()
    }
}