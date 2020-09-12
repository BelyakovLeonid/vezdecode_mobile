package com.belyakov.vezdecode.presentation.start

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.VezdecodeApp
import com.belyakov.vezdecode.di.ViewModelFactory
import kotlinx.android.synthetic.main.f_start.*
import javax.inject.Inject

class StartFragment : Fragment(R.layout.f_start) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: StartViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        VezdecodeApp.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        targetButton.setOnClickListener {
            viewModel.setDonateType(DonateType.TARGET)
            findNavController().navigate(R.id.action_to_targetSetupFragment)
        }
        regularButton.setOnClickListener {
            viewModel.setDonateType(DonateType.REGULAR)
            findNavController().navigate(R.id.action_to_regularSetupFragment)
        }
        back.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}
