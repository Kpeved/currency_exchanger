package com.test.feature_exchange.ui

import com.test.feature_exchange.model.CurrencyUiModel

sealed class CurrencyExchangeUiState {
    class CurrenciesUpdated(val items: List<CurrencyUiModel>, val initialSetup : Boolean) : CurrencyExchangeUiState()
    object Loading : CurrencyExchangeUiState()
    class BaseCurrencyChanged(val baseCurrency: CurrencyUiModel, val items: List<CurrencyUiModel>, val fromPosition: Int) : CurrencyExchangeUiState()
    object Error : CurrencyExchangeUiState()
}