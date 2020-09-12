package com.belyakov.vezdecode.utils

import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import android.widget.EditText

fun View.getStyleAttrs(
    set: AttributeSet?,
    attr: IntArray,
    defStyleAttr: Int,
    actionWithRes: (TypedArray) -> Unit
) {
    context.theme.obtainStyledAttributes(set, attr, defStyleAttr, 0).apply {
        try {
            actionWithRes(this)
        } finally {
            recycle()
        }
    }
}

fun View.dpToPx(dp: Int) = resources.displayMetrics.density * dp