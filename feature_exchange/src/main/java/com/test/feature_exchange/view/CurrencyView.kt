package com.test.feature_exchange.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.test.core.ext.afterTextChanged
import com.test.feature_exchange.R
import com.test.feature_exchange.model.CurrencyUiModel
import com.test.feature_exchange.ui.DecimalDigitsInputFilter
import kotlinx.android.synthetic.main.currency_list_view.view.*
import java.text.DecimalFormatSymbols

class CurrencyView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var onAmountChanged: ((amount: String) -> Unit)? = null
    private var disableEditingCallback = false

    init {
        LayoutInflater.from(context).inflate(R.layout.currency_list_view, this, true)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        editAmount.filters += DecimalDigitsInputFilter(
            2,
            DecimalFormatSymbols.getInstance().decimalSeparator
        )

        editAmount.afterTextChanged {
            if (!disableEditingCallback) {
                onAmountChanged?.invoke(it.toString())
            }
        }
    }

    fun bindData(uiModel: CurrencyUiModel) {
        disableEditingCallback = true
        currencyName.text = uiModel.currencyName
        editAmount.setText(uiModel.currencyValue)
        currencyCode.text = uiModel.currencySign
        Glide.with(this)
            .load(uiModel.flagUrl)
            .into(ivFlag)

        disableEditingCallback = false
    }

    fun updateCurrencyText(currencyValue: String?) {
        disableEditingCallback = true
        editAmount.setText(currencyValue)
        disableEditingCallback = false
    }

    fun requestEditTextFocus() {
        editAmount.requestFocus()
    }
}