package com.test.feature_exchange.ui

import androidx.recyclerview.widget.DiffUtil
import com.test.feature_exchange.model.CurrencyUiModel

class CurrencyDiffUtilsCallback(
    private val newItems: List<CurrencyUiModel>,
    private val oldItems: List<CurrencyUiModel>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition].currencySign == newItems[newItemPosition].currencySign

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldItems[oldItemPosition] == newItems[newItemPosition]

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int) =
        newItems[newItemPosition].currencyValue
}
