package com.test.feature_exchange.ui

import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import java.util.regex.Pattern

class DecimalDigitsInputFilter(
    fractionDigits: Int,
    decimalSeparator: Char
) : InputFilter {

    private val pattern: Pattern =
        Pattern.compile("^(?!0[0-9])\\d{1,}(\\$decimalSeparator\\d{0,$fractionDigits})?$")

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): CharSequence? {

        val match = TextUtils.concat(
            dest.subSequence(0, dstart),
            source.subSequence(start, end),
            dest.subSequence(dend, dest.length)
        )

        val matcher = pattern.matcher(match)

        return if (!matcher.matches()) "" else null
    }
}
