package com.belyakov.vezdecode.presentation.feed

import androidx.appcompat.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.belyakov.vezdecode.R

class ExitDialog : DialogFragment() {

    companion object {
        const val TAG = "exit_dialog"

        fun getInstance() = ExitDialog()
    }

    var onConfirmListener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext(), R.style.AlertDialog)
            .setMessage(R.string.new_donate)
            .setNegativeButton(R.string.no) { _, _ -> dismiss() }
            .setPositiveButton(R.string.yes) { _, _ ->
                onConfirmListener?.invoke()
                dismiss()
            }
            .create()
    }
}