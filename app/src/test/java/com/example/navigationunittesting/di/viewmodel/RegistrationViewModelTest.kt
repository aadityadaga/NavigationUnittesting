package com.example.navigationunittesting.di.viewmodel

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import com.example.navigationunittesting.FullDataDisplayActivity
import com.example.navigationunittesting.database.RegistrationDAO
import com.example.navigationunittesting.database.RegistrationDatabase
import com.example.navigationunittesting.model.RegistrationModel
import org.junit.After
import org.junit.Assert.assertNotNull
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
import kotlin.test.assertEquals
import kotlin.test.assertFalse


@LargeTest
@Config(sdk = [29], application = KoinTestApp::class)
@RunWith(RobolectricTestRunner::class)
class RegistrationViewModelTest : AutoCloseKoinTest() {

    private lateinit var mRegistrationModel: RegistrationModel
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
            get() as DataDisplayViewModel
        }


    }


    @Before
    fun setUp() {
        stopKoin()
        MockitoAnnotations.openMocks(this);
        app = ApplicationProvider.getApplicationContext()
        startKoin {
            androidContext(app)
            modules(emptyList())
        }

    }

    @After
    fun tearDown() {
        stopKoin()
    }


    @Test
    fun testNull() {
        app.loadModules(configureLocalModuleTest(app)) {
            val mData = mRegistrationDAO.getAllData()
            assertNotNull(mData)
        }
    }

    @Test
    fun insertData() {
        app.loadModules(configureLocalModuleTest(app)) {
            mRegistrationModel = RegistrationModel(
                "test",
                "Noida", "", "test@gmail.com"
            )
            mRegistrationDAO.insertData(mRegistrationModel)

        }
    }


    @Test
    fun testNotNull() {
        app.loadModules(configureLocalModuleTest(app)) {
            val mData = mRegistrationDAO.getAllData()
            assertNotNull(mData)
        }
    }

    @Test
    fun testForActivityOpen() {
        app.loadModules(configureLocalModuleTest(app)) {
            mRegistrationModel = RegistrationModel(
                "test",
                "Noida", "", "test@gmail.com"
            )
            val mIntent = Intent(app, FullDataDisplayActivity::class.java).apply {
                putExtra("registrationModel", mRegistrationModel)

            }
            val mResultLauncher =
                ActivityResultContracts
                    .StartActivityForResult()
            mResultLauncher.createIntent(app, mIntent)


            assertEquals(
                mRegistrationModel,
                mResultLauncher.parseResult(1, mIntent).data?.getParcelableExtra(
                    "registrationModel"
                )
            );

        }
    }

    @Test
    fun deleteData() {
        app.loadModules(configureLocalModuleTest(app)) {
            val mRegistrationModel = RegistrationModel(
                "test",
                "Noida", "", "test@gmail.com"
            )
            mRegistrationDAO.deleteRow(mRegistrationModel)
            val mData = mRegistrationDAO.getAllData()

            assertFalse {
                (mData.contains(mRegistrationModel))
            }

        }
    }


}


