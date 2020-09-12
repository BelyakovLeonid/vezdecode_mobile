package com.belyakov.vezdecode.utils

import java.text.SimpleDateFormat
import java.util.*

fun Calendar.toDateString(): String {
    val simpleDateFormat = SimpleDateFormat("d MMMM", Locale.getDefault())
    return simpleDateFormat.format(this.time)
}