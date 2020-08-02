package com.test.core.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat

object PriceUtils {

    fun convertAmountToString(amount: BigDecimal, decimalFormat: DecimalFormat = DECIMAL_FORMAT_DEFAULT): String =
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            ""
        } else {
            decimalFormat
                .format(amount)
                .replace(decimalFormat.decimalFormatSymbols.groupingSeparator.toString(), "")
        }

    fun convertStringToAmount(string: String): BigDecimal =
        if (string.isBlank()) BigDecimal.ZERO
        else BigDecimal.valueOf(string.toDouble())

    private fun getNumberFormat(decimalDigitSize: Int): DecimalFormat =
        (NumberFormat.getCurrencyInstance() as DecimalFormat).apply {
            val symbols = decimalFormatSymbols
            symbols.currencySymbol = ""
            decimalFormatSymbols = symbols
            isGroupingUsed = false
            minimumFractionDigits = 0
            maximumFractionDigits = decimalDigitSize
        }

    private const val DECIMAL_DIGIT_SIZE = 2
    private val DECIMAL_FORMAT_DEFAULT: DecimalFormat =
        getNumberFormat(DECIMAL_DIGIT_SIZE)

}