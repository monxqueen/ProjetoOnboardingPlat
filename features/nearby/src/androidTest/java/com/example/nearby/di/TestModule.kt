package com.example.nearby.di

import com.example.common.utils.FileReader
import com.example.nearby.NearbyFragmentRobot
import com.example.nearby.presentation.NearbyFragment
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal class TestModule {
    private val modules = module {
        factory { NearbyFragmentRobot() }
        factory { FileReader() }
        factory { NearbyFragment() }
    }

    fun load() = loadKoinModules(modules)
}