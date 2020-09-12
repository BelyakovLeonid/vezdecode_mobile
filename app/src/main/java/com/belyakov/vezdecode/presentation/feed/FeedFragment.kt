package com.belyakov.vezdecode.presentation.feed

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.VezdecodeApp
import com.belyakov.vezdecode.di.ViewModelFactory
import com.belyakov.vezdecode.utils.makeCurrencyString
import com.belyakov.vezdecode.utils.observe
import com.belyakov.vezdecode.utils.shortToast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.v_donate_post.*
import javax.inject.Inject

class FeedFragment : Fragment(R.layout.f_feed) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FeedViewModel by viewModels { viewModelFactory }

    private val backPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            showExitDialog()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        VezdecodeApp.appComponent.inject(this)
        requireActivity().onBackPressedDispatcher.addCallback(backPressedCallback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onCreate()
        observeViewModel()
        handleView()
    }

    private fun observeViewModel() {
        observe(viewModel.donateImage) {
            loadPhoto(it)
        }
        observe(viewModel.donateName) {
            donateTitle.text = it
        }
        observe(viewModel.donateAuthor) {
            personName.text = it
            donateAuthor.text = it
        }
        observe(viewModel.donateFeedDescription) {
            text.text = it
        }
        observe(viewModel.donateProgress) {
            donateProgress.progress = it.currentProgress
            helpButton.isEnabled = it.currentMoney != it.allMoney
            donateProgressTitle.text = when (it.currentMoney) {
                0L -> getString(R.string.donate_first)
                it.allMoney -> getString(
                    R.string.donate_complete,
                    requireContext().makeCurrencyString(it.allMoney)
                )
                else -> obtainProgressString(it.currentMoney, it.allMoney)
            }
        }
        observe(viewModel.donateDate) {
            donateExpire.text = if (it.isRegular) {
                getString(R.string.donate_monthly)
            } else {
                obtainExpireText(it.date)
            }
        }
    }

    private fun loadPhoto(uri: Uri?) {
        Glide.with(requireContext())
            .load(uri)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(donatePicture)
    }

    private fun obtainProgressString(currentMoney: Long, allMoney: Long?): String {
        val firstCurrency = requireContext().makeCurrencyString(currentMoney)
        val secondCurrency = requireContext().makeCurrencyString(allMoney)
        return getString(R.string.donate_progress, firstCurrency, secondCurrency)
    }

    private fun obtainExpireText(date: String?): String {
        return getString(R.string.donate_monthly) //todo
    }

    private fun showExitDialog() {
        val dialog = ExitDialog.getInstance()
        dialog.onConfirmListener = {
            viewModel.clearData()
            findNavController().navigateUp()
        }
        dialog.show(childFragmentManager, ExitDialog.TAG)
    }

    private fun handleView() {
        donateCard.setOnClickListener {
            findNavController().navigate(R.id.action_to_feedDetailsFragment)
        }
        helpButton.setOnClickListener {
            viewModel.onHelpClicked()
        }
    }

    override fun onDetach() {
        backPressedCallback.isEnabled = false
        super.onDetach()
    }
}
