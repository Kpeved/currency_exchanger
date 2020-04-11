package com.test.feature_exchange.model

import java.math.BigDecimal

class CurrenciesMutableModel(
    var baseCurrency: String,
    var baseValue: BigDecimal,
    var currenciesMap: Map<String, BigDecimal>,
    var currenciesList: MutableList<Currency>
) {
    class Currency(
        val name: String,
        val sign: String,
        val url: String,
        var value: BigDecimal
    )
}