package com.example.favorite.di

import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

abstract class FeatureModule {
    private val modules: List<Module> get() = presentationModule + domainModule
    protected open val presentationModule: Module = module {}
    protected open val domainModule: Module = module {}

    fun load() {
        loadKoinModules(modules)
    }
}
