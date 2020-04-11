package com.test.feature_exchange_network.di

import com.test.feature_exchange_network.service.ExchangeService
import org.koin.dsl.module
import retrofit2.Retrofit

object ExchangeNetworkModule {

    fun createExchangeNetworkModule() = module {
        single { provideExchangeService(get()) }
    }

    private fun provideExchangeService(retrofitClient: Retrofit) = retrofitClient.create(ExchangeService::class.java)

}