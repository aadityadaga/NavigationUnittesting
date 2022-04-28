package com.example.navigationunittesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.navigationunittesting.databinding.ActivityBottomNavigationBinding
import com.example.navigationunittesting.ui.fragment.DataDisplayFragment
import com.example.navigationunittesting.ui.fragment.RegistrationFragment
import com.example.navigationunittesting.ui.fragment.SettingFragment


class BottomNavigationActivity : AppCompatActivity() {


    private lateinit var mActivityBottomNavigationBinding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityBottomNavigationBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation)
        mActivityBottomNavigationBinding.lifecycleOwner = this
        replaceFragment(RegistrationFragment())
        mActivityBottomNavigationBinding.bottomNavigation

        mActivityBottomNavigationBinding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {

                    replaceFragment(RegistrationFragment())
                }
                R.id.item_list -> {
                    replaceFragment(DataDisplayFragment())
                }
                R.id.item_setting -> {
                    replaceFragment(SettingFragment())
                }

                else -> {

                }
            }
            return@setOnItemSelectedListener true

        }
    }

    private fun replaceFragment(mFragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, mFragment)
            .commit()
    }
}