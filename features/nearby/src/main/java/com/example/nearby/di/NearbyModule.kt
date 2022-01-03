package com.example.nearby.di

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class NearbyModule {

    private val modules = module {

        // DATA

        // DOMAIN

        // PRESENTATION
    }

    fun load() = loadKoinModules(modules)
}