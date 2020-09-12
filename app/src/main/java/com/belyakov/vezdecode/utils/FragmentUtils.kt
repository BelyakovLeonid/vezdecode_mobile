package com.belyakov.vezdecode.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.shortToast(strId: Int) =
    Toast.makeText(requireContext(), getString(strId), Toast.LENGTH_SHORT).show()