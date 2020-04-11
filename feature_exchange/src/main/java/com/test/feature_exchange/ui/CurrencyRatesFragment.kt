package com.test.feature_exchange.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.use
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.core.ext.gone
import com.test.core.ext.show
import com.test.core.utils.KeyboardUtils.showKeyboard
import com.test.feature_exchange.R
import com.test.feature_exchange.model.CurrencyUiModel
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.error_layout.view.*
import kotlinx.android.synthetic.main.fragment_currency_rates.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class CurrencyRatesFragment : Fragment() {

    private val adapter = CurrencyAdapter()

    private val viewModel: CurrencyRatesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_currency_rates, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViewModelState()
        setUpRecyclerView()

        topListItem.onAmountChanged = {
            viewModel.mainCurrencyEdited(it)
        }
        btnRetry.setOnClickListener {
            viewModel.startUpdatingCurrencies()
        }
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter.itemClickCallback = { position ->
            viewModel.newMainCurrencySelected(position)
        }

        recyclerView.setHasFixedSize(true)
        val itemAnimator: DefaultItemAnimator = object : DefaultItemAnimator() {
            override fun canReuseUpdatedViewHolder(viewHolder: RecyclerView.ViewHolder): Boolean {
                return true
            }
        }
        recyclerView.itemAnimator = itemAnimator
    }

    private fun setUpViewModelState() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is CurrencyExchangeUiState.CurrenciesUpdated -> handleCurrenciesUpdated(it.items, it.initialSetup)
                is CurrencyExchangeUiState.Error -> handleError()
                CurrencyExchangeUiState.Loading -> handleLoading()
                is CurrencyExchangeUiState.BaseCurrencyChanged ->
                    handleBaseCurrencyChanged(
                        it.baseCurrency,
                        it.items,
                        it.fromPosition
                    )
            }
        })
    }

    @SuppressLint("Recycle")
    private fun handleError() {
        progressBar.gone()
        errorLayout.show()
        errorLayout.tvQuote.text = resources.getStringArray(R.array.quotes).random()

        resources.obtainTypedArray(R.array.error_pics).use { array ->
            errorLayout.ivError.setImageResource(array.getResourceId(Random.nextInt(array.length()), -1))
        }

    }

    private fun handleLoading() {
        progressBar.show()
        topListItem.gone()
        recyclerView.gone()
        errorLayout.gone()
    }

    private fun handleCurrenciesUpdated(items: List<CurrencyUiModel>, initialSetup: Boolean) {
        progressBar.gone()
        topListItem.show()
        recyclerView.show()
        errorLayout.gone()
        adapter.bindData(items)
        if (initialSetup) {
            topListItem.bindData(items.first())
        }
    }

    private fun handleBaseCurrencyChanged(
        baseCurrency: CurrencyUiModel,
        items: List<CurrencyUiModel>,
        fromPosition: Int
    ) {
        adapter.bindDataItemMoved(items, fromPosition)
        recyclerView.scrollToPosition(0)
        updateBaseCurrency(baseCurrency)
    }

    private fun updateBaseCurrency(baseCurrency: CurrencyUiModel) {
        topListItem.bindData(baseCurrency)
        topListItem.requestEditTextFocus()
        activity?.let {
            showKeyboard(it)
        }
    }

    override fun onResume() {
        viewModel.startUpdatingCurrencies()
        super.onResume()
    }

    override fun onPause() {
        viewModel.stopUpdatingCurrencies()
        super.onPause()
    }
}
