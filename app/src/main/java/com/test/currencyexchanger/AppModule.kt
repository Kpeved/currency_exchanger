package com.test.currencyexchanger

import com.test.core_network.di.Qualifiers
import org.koin.dsl.module

object AppModule {
    fun createAppModule() = module {
        single(qualifier = Qualifiers.BASE_URL) { BuildConfig.BASE_URL }
    }
}