package com.example.navigationunittesting.di.viewmodel

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import com.example.navigationunittesting.database.RegistrationDAO
import com.example.navigationunittesting.database.RegistrationDatabase
import com.example.navigationunittesting.model.RegistrationModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.test.assertFalse
import kotlin.test.assertTrue


@LargeTest
@Config(sdk = [29], application = KoinTestApp::class)
@RunWith(RobolectricTestRunner::class)
class RegistrationFragmentTestCases : AutoCloseKoinTest() {


    private val mRegistrationViewModel: RegistrationViewModel by inject()
    private val mRegistrationDAO: RegistrationDAO by inject()

    private lateinit var app: KoinTestApp

    private fun configureLocalModuleTest(context: Context) = module {
        single {
            Room.inMemoryDatabaseBuilder(context, RegistrationDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }
        factory { (get() as RegistrationDatabase).registrationDao() }
        viewModel {
            RegistrationViewModel(get() as RegistrationDAO, app)
        }


    }

    @Before
    fun setUp() {
        stopKoin()
        MockitoAnnotations.openMocks(this)
        app = ApplicationProvider.getApplicationContext()
        startKoin {
            androidContext(app)
            modules(emptyList())
        }

    }

    @Test
    fun checkInvalidEmail() {
        app.loadModules(configureLocalModuleTest(app)) {
            assertFalse(mRegistrationViewModel.isValidEmailID("aditya.daga"))
        }
    }


    @Test
    fun checkValidEmail() {
        app.loadModules(configureLocalModuleTest(app)) {
            assertTrue(mRegistrationViewModel.isValidEmailID("aditya.daga01@gmail.com"))
        }
    }

    @Test
    fun insertDataIntoDatabase() {
        app.loadModules(configureLocalModuleTest(app)) {

            val mRegistrationModel = RegistrationModel("RegistrationFragmentTest",
            "RegistrationFragment",
            "",
            "RegistrationFragment@gmail.com")
            mRegistrationViewModel.insertData(mRegistrationModel)

            val mData = mRegistrationDAO.getAllData()

            assertTrue {
                (mData.contains(mRegistrationModel))
            }
        }
    }
}