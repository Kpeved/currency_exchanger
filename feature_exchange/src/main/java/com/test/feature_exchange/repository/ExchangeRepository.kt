package com.test.feature_exchange.repository

import com.test.feature_exchange_network.service.ExchangeService
import com.test.feature_exchange_network.service.MockedExchangeService

class ExchangeRepository(private val service: ExchangeService) {

    // Server is not working anymore
//    fun retrieveData(currency: String?) = service.getExchanges(currency)
    fun retrieveData(currency: String?) = MockedExchangeService().getExchanges(currency)
}