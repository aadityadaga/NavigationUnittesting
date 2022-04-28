package com.example.navigationunittesting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationunittesting.R
import com.example.navigationunittesting.databinding.DataDisplayBinding
import com.example.navigationunittesting.model.RegistrationModel

class DataDisplayAdapter(
    var list: List<RegistrationModel?>?, var mProductItemClickListener:
    ProductItemClickListener
) :
    RecyclerView.Adapter<DataDisplayAdapter.DataDisplayViewHolder>() {


    private lateinit var mDisplayBinding: DataDisplayBinding

    inner class DataDisplayViewHolder(mDisplayBinding: DataDisplayBinding) :
        RecyclerView.ViewHolder(
            mDisplayBinding.root
        ) {
        fun bind(mRegistrationModel: RegistrationModel, listener: ProductItemClickListener) {
            mDisplayBinding.listview = mRegistrationModel
            mDisplayBinding.executePendingBindings()
            mDisplayBinding.constraintLayout.setOnClickListener {
                listener.onProductItemClicked(mRegistrationModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataDisplayViewHolder {
        mDisplayBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.data_display, parent, false
        )

        return DataDisplayViewHolder(mDisplayBinding!!)
    }

    override fun onBindViewHolder(holder: DataDisplayViewHolder, position: Int) {
        val mRegistrationModel = list!![position]
        if (mRegistrationModel != null) {
            holder.bind(mRegistrationModel, mProductItemClickListener)
        }

    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }


    interface ProductItemClickListener {
        fun onProductItemClicked(mRegistrationModel: RegistrationModel)
    }
}