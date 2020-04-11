package com.test.feature_exchange.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.test.feature_exchange.R
import com.test.feature_exchange.model.CurrencyUiModel
import kotlinx.android.synthetic.main.currency_list_item.view.*
import kotlinx.android.synthetic.main.currency_list_view.view.*
import kotlin.math.min

class CurrencyAdapter : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private var items: List<CurrencyUiModel> = emptyList()
    var itemClickCallback: ((position: Int) -> Unit)? = null
    private var focusOnFirstElement = false

    fun bindData(newItems: List<CurrencyUiModel>) {
        val diff = DiffUtil.calculateDiff(CurrencyDiffUtilsCallback(newItems, items))
        diff.dispatchUpdatesTo(this)
        this.items = ArrayList(newItems)
    }

    fun bindDataItemMoved(newItems: List<CurrencyUiModel>, fromPosition: Int) {
        this.items = newItems
        focusOnFirstElement = true

        notifyItemMoved(fromPosition, 0)
        notifyItemChanged(0)
        notifyItemChanged(min(1, newItems.size - 1))
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        constructor(parent: ViewGroup)
                : this(
            LayoutInflater.from(parent.context).inflate(R.layout.currency_list_item, parent, false)
        )

        init {
            itemView.setOnClickListener { itemClickCallback?.invoke(adapterPosition) }
            itemView.editAmount.apply {
                movementMethod = null
                keyListener = null
                isClickable = false
                isFocusable = false
            }
        }

        fun bind(uiModel: CurrencyUiModel) {
            itemView.currencyView.bindData(uiModel)
        }

        fun updateCurrencyValue(currencyValue: String?) {
            itemView.currencyView.updateCurrencyText(currencyValue)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        payloads.firstOrNull()?.let {
            holder.updateCurrencyValue(it as String)
        } ?: let {
            this.onBindViewHolder(holder, position)
        }
    }
}