package com.example.favorite.di

import com.example.favorite.FavoriteFragmentRobot
import com.example.favorite.remote.utils.FileReader
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class TestModule {

    private val modules = module {

        factory { FavoriteFragmentRobot() }
        factory { FileReader() }
    }

    fun load() = loadKoinModules(modules)
}