package com.example.navigationunittesting.controller

import android.app.Application
import androidx.room.Room
import com.example.navigationunittesting.database.RegistrationDAO
import com.example.navigationunittesting.database.RegistrationDatabase
import com.example.navigationunittesting.di.viewmodel.DataDisplayViewModel
import com.example.navigationunittesting.di.viewmodel.FullDataViewModel
import com.example.navigationunittesting.di.viewmodel.RegistrationViewModel
import com.example.navigationunittesting.di.viewmodel.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val databaseModule = module {

    single {
        createAppDatabase(get())
    }


    single { provideDao(get()) }
}

fun provideDao(dataBase: RegistrationDatabase): RegistrationDAO {
    return dataBase.registrationDao()
}

internal fun createAppDatabase(application: Application): RegistrationDatabase {
    return Room.databaseBuilder(
        application,
        RegistrationDatabase::class.java,
        "RegistrationDatabase"
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}



val allViewModel = module {
    viewModel {
        RegistrationViewModel(get(), get())
    }
    viewModel {
        DataDisplayViewModel(get())
    }
    viewModel {
        SettingViewModel()
    }
    viewModel {
        FullDataViewModel()
    }

}
