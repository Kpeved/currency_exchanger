package com.test.feature_exchange.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.test.core.ext.addTo
import com.test.core.livedata.SingleLiveEvent
import com.test.core.utils.PriceUtils
import com.test.feature_exchange.model.CurrenciesMutableModel
import com.test.feature_exchange.repository.ExchangeRepository
import com.test.feature_exchange_network.model.CurrenciesModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.concurrent.TimeUnit

class CurrencyRatesViewModel(
    private val exchangeRepository: ExchangeRepository,
    private val converter: CurrencyRatesConverter
) : ViewModel() {

    private var disposables = CompositeDisposable()
    private val _uiState = SingleLiveEvent<CurrencyExchangeUiState>()
    val uiState: LiveData<CurrencyExchangeUiState> = _uiState
    private var shouldSetInitialData = true

    private var currenciesMutableModel: CurrenciesMutableModel? = null

    fun startUpdatingCurrencies() {
        if (currenciesMutableModel != null) {
            sendInitialState()
        } else {
            _uiState.value = CurrencyExchangeUiState.Loading
        }

        var requestFinished = true
        Observable.interval(0, LOADING_PERIOD, TimeUnit.SECONDS, Schedulers.io())
            .filter { requestFinished }
            .doOnNext { requestFinished = false }
            .flatMap { exchangeRepository.retrieveData(currenciesMutableModel?.baseCurrency).toObservable() }
            .map {
                requestFinished = true
                updateCurrencies(it)
            }
            .map { converter.convertToUiItems(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe({
                _uiState.value = CurrencyExchangeUiState.CurrenciesUpdated(it, shouldSetInitialData)
                shouldSetInitialData = false
            }, {
                _uiState.value = CurrencyExchangeUiState.Error
                Timber.e(it)

            })
            .addTo(disposables)

    }

    private fun sendInitialState() {
        currenciesMutableModel?.let {
            _uiState.value = CurrencyExchangeUiState.CurrenciesUpdated(converter.convertToUiItems(it), true)
        }
    }

    private fun updateCurrencies(currencies: CurrenciesModel): CurrenciesMutableModel {
        val model = currenciesMutableModel?.let {
            converter.updateCurrencyMutableModel(it, currencies)
            it
        } ?: run {
            converter.createCurrencyMutableModel(currencies)
        }
        currenciesMutableModel = model
        return model
    }

    fun mainCurrencyEdited(value: String) {
        currenciesMutableModel?.let {
            it.baseValue = PriceUtils.convertStringToAmount(value)
            converter.recalculateCurrencyValues(it)
            _uiState.value = CurrencyExchangeUiState.CurrenciesUpdated(converter.convertToUiItems(it), shouldSetInitialData)
        }
    }

    fun stopUpdatingCurrencies() {
        disposables.dispose()
        disposables = CompositeDisposable()
    }

    fun newMainCurrencySelected(position: Int) {
        currenciesMutableModel?.apply {
            val newList = ArrayList(currenciesList)
            val item = newList[position]
            newList.removeAt(position)
            newList.add(0, item)
            currenciesList = newList
            baseCurrency = item.sign
            baseValue = item.value

            val uiItems = converter.convertToUiItems(this)
            _uiState.value = CurrencyExchangeUiState.BaseCurrencyChanged(
                uiItems.first(),
                uiItems,
                position
            )
        }
    }

    companion object {
        private const val LOADING_PERIOD = 1L
    }
}