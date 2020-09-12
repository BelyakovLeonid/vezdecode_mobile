package com.belyakov.vezdecode.presentation.setup_extra

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionManager
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.VezdecodeApp
import com.belyakov.vezdecode.di.ViewModelFactory
import com.belyakov.vezdecode.utils.observe
import com.belyakov.vezdecode.utils.toDateString
import kotlinx.android.synthetic.main.f_setup_extra.*
import java.util.*
import javax.inject.Inject

class SetupExtraFragment : Fragment(R.layout.f_setup_extra) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SetupExtraViewModel by viewModels { viewModelFactory }
    private var calendar: Calendar? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        VezdecodeApp.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.setAuthor(getString(R.string.author_name))
        observe(viewModel.editIsComplete) {
            buttonNext.isEnabled = it
        }
    }

    private fun handleView() {
        extraExpireType.setOnCheckedChangeListener { _, id ->
            val expireByDate = id == R.id.exactDate
            viewModel.setExpireByDate(expireByDate)
            showDateField(expireByDate)
            if (expireByDate) {
                clearDate()
            }
        }
        extraDate.onClickListener = {
            showDateDialog()
        }
        back.setOnClickListener {
            findNavController().navigateUp()
        }
        buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_setupExtraFragment_to_setupDetailsFragment)
        }
    }

    private fun showDateField(show: Boolean) {
        TransitionManager.beginDelayedTransition(fragmentRoot)
        extraDate.isVisible = show
    }

    private fun clearDate() {
        calendar = null
        extraDate.setText(getString(R.string.donate_date_subtitle))
        viewModel.setExpireDate(NOT_SET)
    }

    private fun showDateDialog() {
        if (calendar == null) {
            calendar = Calendar.getInstance()
        }

        calendar?.let {
            val dateListener = DatePickerDialog.OnDateSetListener { _, y, m, d ->
                it.set(Calendar.YEAR, y)
                it.set(Calendar.MONTH, m)
                it.set(Calendar.DAY_OF_MONTH, d)
                extraDate.setText(it.toDateString())
                viewModel.setExpireDate(it.timeInMillis)
            }

            DatePickerDialog(
                requireContext(),
                dateListener,
                it.get(Calendar.YEAR),
                it.get(Calendar.MONTH),
                it.get(Calendar.DAY_OF_MONTH)
            ).apply {
                datePicker.minDate = it.timeInMillis
                window?.setFlags(FLAG_NOT_FOCUSABLE, FLAG_NOT_FOCUSABLE)
                show()
            }
        }
    }
}
