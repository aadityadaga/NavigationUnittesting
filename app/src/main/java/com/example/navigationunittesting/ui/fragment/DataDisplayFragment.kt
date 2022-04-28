package com.example.navigationunittesting.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.navigationunittesting.FullDataDisplayActivity
import com.example.navigationunittesting.adapter.DataDisplayAdapter
import com.example.navigationunittesting.databinding.FragmentDataDisplayBinding
import com.example.navigationunittesting.di.viewmodel.DataDisplayViewModel
import com.example.navigationunittesting.model.RegistrationModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class DataDisplayFragment : Fragment(), DataDisplayAdapter.ProductItemClickListener {


    private lateinit var mFragmentDataDisplayBinding: FragmentDataDisplayBinding
    private val mDataDisplayViewModel by viewModel<DataDisplayViewModel>()
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mFragmentDataDisplayBinding =
            FragmentDataDisplayBinding.inflate(
                inflater, container, false
            )

        mFragmentDataDisplayBinding.datadisplay = mDataDisplayViewModel
        mFragmentDataDisplayBinding.lifecycleOwner = this

        getData()


        return mFragmentDataDisplayBinding.root
    }

    fun getData() {
        mFragmentDataDisplayBinding.shimmerViewContainer.visibility = View.VISIBLE
        mFragmentDataDisplayBinding.recyclerView.visibility = View.GONE

        mDataDisplayViewModel.getData().observe(viewLifecycleOwner) {

            if (it?.isEmpty()!!) {
                mFragmentDataDisplayBinding.shimmerViewContainer.visibility = View.GONE
                mFragmentDataDisplayBinding.recyclerView.visibility = View.GONE
                mFragmentDataDisplayBinding.textViewNoData.visibility = View.VISIBLE
            } else {
                mFragmentDataDisplayBinding.shimmerViewContainer.visibility = View.GONE
                mFragmentDataDisplayBinding.recyclerView.visibility = View.VISIBLE
                mFragmentDataDisplayBinding.textViewNoData.visibility = View.GONE
                mFragmentDataDisplayBinding.recyclerView.adapter = DataDisplayAdapter(
                    it,
                    this@DataDisplayFragment
                )
            }
        }
    }

    override fun onProductItemClicked(mRegistrationModel: RegistrationModel) {
        val mIntent = Intent(mContext, FullDataDisplayActivity::class.java).apply {
            putExtra("registrationModel", mRegistrationModel)

    }
        mResultLauncher.launch(mIntent)

    }


    private var mResultLauncher = registerForActivityResult(
        ActivityResultContracts
            .StartActivityForResult()
    ) {
        if (it.resultCode == 100) {
            val data: Intent? = it.data
            val mRegistrationModel =
                data?.extras?.getParcelable<RegistrationModel>("registrationModel")
            mDataDisplayViewModel.deleteData(mRegistrationModel!!)
            getData()
        }
    }

}