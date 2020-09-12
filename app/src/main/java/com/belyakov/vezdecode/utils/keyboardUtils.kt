package com.belyakov.vezdecode.utils

import android.app.Activity
import android.content.Context
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import android.widget.EditText


fun EditText.showKeyboard() {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        windowInsetsController?.show(WindowInsets.Type.ime())
    } else {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
            )
        requestFocus()
    }
}

fun EditText.hideKeyboard() {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
        windowInsetsController?.hide(WindowInsets.Type.ime())
    } else {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0)
    }
}