package com.test.feature_exchange.ui

import android.content.Context
import com.test.core.utils.PriceUtils
import com.test.core_localization.CurrencyNames
import com.test.feature_exchange.R
import com.test.feature_exchange.model.CurrenciesMutableModel
import com.test.feature_exchange.model.CurrencyUiModel
import com.test.feature_exchange_network.model.CurrenciesModel
import java.math.BigDecimal

class CurrencyRatesConverter(private val context: Context) {
    fun createCurrencyMutableModel(currencies: CurrenciesModel): CurrenciesMutableModel =
        CurrenciesMutableModel(
            baseCurrency = currencies.baseCurrency,
            baseValue = BigDecimal.ONE,
            currenciesMap = currencies.rates,
            currenciesList = createCurrenciesList(currencies)
        )

    fun convertToUiItems(model: CurrenciesMutableModel): List<CurrencyUiModel> =
        model.currenciesList.map {
            CurrencyUiModel(
                currencySign = it.sign,
                currencyName = it.name,
                flagUrl = it.url,
                currencyValue = PriceUtils.convertAmountToString(it.value)
            )
        }

    fun updateCurrencyMutableModel(
        currenciesMutableModel: CurrenciesMutableModel,
        currencies: CurrenciesModel
    ) {
        currenciesMutableModel.apply {
            currenciesMap = currencies.rates
            recalculateCurrencyValues(this)
        }
    }

    fun recalculateCurrencyValues(
        currenciesMutableModel: CurrenciesMutableModel
    ) {
        currenciesMutableModel.apply {
            currenciesList.forEach {
                it.value = baseValue * (currenciesMap[it.sign] ?: BigDecimal.ONE)
            }
        }
    }

    private fun createCurrenciesList(currencies: CurrenciesModel): MutableList<CurrenciesMutableModel.Currency> {
        val result = mutableListOf(
            CurrenciesMutableModel.Currency(
                sign = currencies.baseCurrency,
                name = CurrencyNames.SUPPORTED_CURRENCIES[currencies.baseCurrency]?.let { context.getString(it.currencyNameId) } ?: "",
                url = composeUrl(currencies.baseCurrency),
                value = BigDecimal.ONE
            )
        )
        currencies.rates.forEach { (code, rate) ->
            result.add(
                CurrenciesMutableModel.Currency(
                    sign = code,
                    name = CurrencyNames.SUPPORTED_CURRENCIES[code]?.let { context.getString(it.currencyNameId) } ?: "",
                    url = composeUrl(code),
                    value = rate
                )
            )
        }
        return result
    }

    private fun composeUrl(currencyCode: String): String =
        CurrencyNames.SUPPORTED_CURRENCIES[currencyCode]?.let { context.getString(R.string.country_flags_base_url, it.countryCode) } ?: ""
}