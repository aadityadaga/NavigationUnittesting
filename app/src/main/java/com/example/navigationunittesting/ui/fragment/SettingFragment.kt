package com.example.navigationunittesting.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationunittesting.R
import com.example.navigationunittesting.databinding.FragmentSettingBinding
import com.example.navigationunittesting.di.viewmodel.SettingViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel


class SettingFragment : Fragment() {


    private val mSettingViewModel by viewModel<SettingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val mFragmentSettingBinding = FragmentSettingBinding.inflate(inflater, container, false)
        mFragmentSettingBinding.lifecycleOwner = this
        mFragmentSettingBinding.setting = mSettingViewModel

        mSettingViewModel.mOnClick.observe(viewLifecycleOwner) {
            if (it == 100) {
                dialogDisplay()
            }
        }

        return mFragmentSettingBinding.root
    }


    private fun dialogDisplay(){
        val mAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext(), R.style.CutShapeTheme)
        mAlertDialogBuilder.setMessage(requireContext().getString(R.string.are_you_sure))
        mAlertDialogBuilder.setPositiveButton("Ok") { dialogInterface, _ ->
            dialogInterface?.cancel()
            (requireContext() as Activity).finish()
        }
        mAlertDialogBuilder.setNegativeButton("Cancel") { dialogInterface, _ ->
            dialogInterface?.cancel()
        }
        val mAlertDialog = mAlertDialogBuilder.create()
        mAlertDialog.setCancelable(false)
        mAlertDialog.show()
    }
}