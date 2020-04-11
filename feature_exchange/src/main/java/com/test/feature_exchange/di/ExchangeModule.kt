package com.test.feature_exchange.di

import com.test.feature_exchange.repository.ExchangeRepository
import com.test.feature_exchange.ui.CurrencyRatesConverter
import com.test.feature_exchange.ui.CurrencyRatesViewModel
import com.test.feature_exchange_network.di.ExchangeNetworkModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ExchangeModule {
    fun createExchangeModules() = module {
        viewModel { CurrencyRatesViewModel(get(), get()) }
        single { ExchangeRepository(get()) }
        single { CurrencyRatesConverter(get()) }
    } + ExchangeNetworkModule.createExchangeNetworkModule()
}