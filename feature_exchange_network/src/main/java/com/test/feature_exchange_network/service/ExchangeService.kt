package com.test.feature_exchange_network.service

import com.test.feature_exchange_network.model.CurrenciesModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import java.math.BigDecimal

interface ExchangeService {
    @GET("latest")
    fun getExchanges(@Query("base") currency: String?): Single<CurrenciesModel>
}

class MockedExchangeService() : ExchangeService {
    override fun getExchanges(currency: String?): Single<CurrenciesModel> =
        Single.just(
            when (currency) {
                "USD" ->
                    CurrenciesModel(
                        "USD", rates = mapOf(
                            Pair("EUR", BigDecimal(0.8)),
                            Pair("GBP", BigDecimal(0.7))
                        )
                    )
                "EUR" -> CurrenciesModel(
                    "EUR", rates = mapOf(
                        Pair("USD", BigDecimal(1.2)),
                        Pair("GBP", BigDecimal(0.9))
                    )
                )
                "GBP" ->
                    CurrenciesModel(
                        "GBP", rates = mapOf(
                            Pair("EUR", BigDecimal(1.2)),
                            Pair("USD", BigDecimal(1.4))
                        )
                    )
                else -> CurrenciesModel(
                    "GBP", rates = mapOf(
                        Pair("EUR", BigDecimal(1.3)),
                        Pair("USD", BigDecimal(1.4))
                    )
                )
            }
        )
}