package com.belyakov.vezdecode.presentation.setup.target

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.VezdecodeApp
import com.belyakov.vezdecode.di.ViewModelFactory
import com.belyakov.vezdecode.utils.createFile
import com.belyakov.vezdecode.utils.observe
import com.belyakov.vezdecode.utils.shortToast
import kotlinx.android.synthetic.main.f_setup_target.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


class SetupTargetFragment : Fragment(R.layout.f_setup_target) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SetupTargetViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        VezdecodeApp.appComponent.inject(this)
    }

    private var imageUri: Uri? = null
    private val takePicture =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success: Boolean ->
            if (success) {
                viewModel.setPhotoUri(imageUri)
                load.loadPhoto(imageUri)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        handleView()
    }

    private fun observeViewModel() {
        observe(viewModel.editIsComplete) {
            buttonNext.isEnabled = it
        }
    }

    private fun handleView() {
        load.setOnClickListener {
            imageUri = FileProvider.getUriForFile(
                requireContext(),
                "com.belyakov.vezdecode",
                createFile(requireContext())
            )
            takePicture.launch(imageUri)
        }
        back.setOnClickListener {
            findNavController().navigateUp()
        }
        load.onRemovePhotoListener = {
            viewModel.setPhotoUri(null)
        }
        setupName.textChangeListener = {
            viewModel.setDonateName(it)
        }
        setupSum.textChangeListener = {
            viewModel.setDonateSum(it)
        }
        setupGoal.textChangeListener = {
            viewModel.setDonateGoal(it)
        }
        setupDescription.textChangeListener = {
            viewModel.setDonateDescription(it)
        }
        setupWallet.onClickListener = {
            shortToast(R.string.dummy_toast)
        }
        buttonNext.setOnClickListener {
            findNavController().navigate(R.id.action_to_setupExtraFragment)
        }
    }
}
