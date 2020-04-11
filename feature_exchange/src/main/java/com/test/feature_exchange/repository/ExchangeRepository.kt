package com.test.feature_exchange.repository

import com.test.feature_exchange_network.service.ExchangeService

class ExchangeRepository(private val service: ExchangeService) {

    fun retrieveData(currency: String?) = service.getExchanges(currency)
}