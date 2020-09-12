package com.belyakov.vezdecode.presentation.setup_details

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.VezdecodeApp
import com.belyakov.vezdecode.di.ViewModelFactory
import com.belyakov.vezdecode.utils.hideKeyboard
import com.belyakov.vezdecode.utils.observe
import com.belyakov.vezdecode.utils.showKeyboard
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.f_setup_details.*
import javax.inject.Inject


class SetupDetailsFragment : Fragment(R.layout.f_setup_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: SetupDetailsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        VezdecodeApp.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleView()
        observeViewModel()
    }

    private fun handleView() {
        contentText.showKeyboard()

        upload.setOnClickListener {
            viewModel.saveFeedDescription(contentText.text.toString())
            findNavController().navigate(R.id.action_to_feedFragment)
        }
        buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeViewModel() {
        observe(viewModel.donateImage) {
            loadPhoto(it)
        }
        observe(viewModel.donateName) {
            donateTitle.text = it
        }
        observe(viewModel.donateAuthor) {
            donateAuthor.text = it
            title.text = it
        }
        observe(viewModel.donateDate) {
            donateExpire.text = if (it.isRegular) {
                getString(R.string.donate_monthly)
            } else {
                obtainExpireText(it.date)
            }
        }
    }

    private fun obtainExpireText(date: String?): CharSequence? {
        return getString(R.string.donate_monthly) //todo
    }

    private fun loadPhoto(uri: Uri?) {
        Glide.with(requireContext())
            .load(uri)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(donatePhoto)
    }

    override fun onStop() {
        super.onStop()
        contentText.hideKeyboard()
    }
}
