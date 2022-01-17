package com.example.nearby.di

import com.example.common.utils.FileReader
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal class TestModule {
    private val modules = module {
        factory { FileReader() }
    }

    fun load() = loadKoinModules(modules)
}