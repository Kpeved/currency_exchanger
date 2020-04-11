package com.test.feature_exchange_network.service

import com.test.feature_exchange_network.model.CurrenciesModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService {
    @GET("latest")
    fun getExchanges(@Query("base") currency: String?): Single<CurrenciesModel>
}