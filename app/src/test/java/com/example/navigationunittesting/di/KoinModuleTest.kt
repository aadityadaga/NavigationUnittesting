package com.example.navigationunittesting.di

import com.example.navigationunittesting.controller.allViewModel
import com.example.navigationunittesting.controller.databaseModule
import org.junit.Rule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

import org.koin.test.check.checkModules

class KoinModuleTest : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(databaseModule, allViewModel)
    }

    @Test
    fun testCoreModule() {
        koinApplication {
            modules(databaseModule, allViewModel)
        }.checkModules()
    }
}