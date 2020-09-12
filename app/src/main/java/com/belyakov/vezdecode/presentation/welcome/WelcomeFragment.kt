package com.belyakov.vezdecode.presentation.welcome

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.belyakov.vezdecode.R
import kotlinx.android.synthetic.main.f_welcome.*

class WelcomeFragment : Fragment(R.layout.f_welcome) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_to_startFragment)
        }
    }
}
