package com.test.feature_exchange_network.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class CurrenciesModel(
    @SerializedName("baseCurrency") val baseCurrency: String,
    @SerializedName("rates") val rates: Map<String, BigDecimal>
)