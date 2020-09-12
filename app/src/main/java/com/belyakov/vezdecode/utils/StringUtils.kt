package com.belyakov.vezdecode.utils

import android.content.Context
import com.belyakov.vezdecode.R
import java.lang.StringBuilder

fun Context.makeCurrencyString(value: Long?): String {
    return getString(R.string.currency_pattern, value.toStringWithSpaces())
}

fun Long?.toStringWithSpaces(): String {
    if (this == null || this == 0L) {
        return "0"
    }

    var tempValue = this ?: 0
    var rankCounter = 0
    val valueString = StringBuilder()
    while (tempValue > 0) {
        rankCounter++
        valueString.append(tempValue.rem(10))
        tempValue = tempValue.div(10)
        if (rankCounter.rem(3) == 0 && tempValue > 0) {
            valueString.append(' ')
        }
    }
    valueString.reverse()
    return valueString.toString()
}
