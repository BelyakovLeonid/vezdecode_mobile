package com.belyakov.vezdecode.presentation.feed_details

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.VezdecodeApp
import com.belyakov.vezdecode.di.ViewModelFactory
import com.belyakov.vezdecode.presentation.feed.DateInfo
import com.belyakov.vezdecode.presentation.feed.ProgressInfo
import com.belyakov.vezdecode.utils.makeCurrencyString
import com.belyakov.vezdecode.utils.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.f_feed_details.*
import kotlinx.android.synthetic.main.f_feed_details.donateProgress
import kotlinx.android.synthetic.main.f_feed_details.donateProgressTitle
import kotlinx.android.synthetic.main.f_feed_details.helpButton
import kotlinx.android.synthetic.main.v_donate_post.*
import javax.inject.Inject

class FeedDetailsFragment : Fragment(R.layout.f_feed_details) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: FeedDetailsViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        VezdecodeApp.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        handleView()
    }

    private fun observeViewModel() {
        observe(viewModel.donateImage) {
            loadPhoto(it)
        }
        observe(viewModel.donateName) {
            title.text = it
        }
        observe(viewModel.donateAuthor) {
            authorText.text = getString(R.string.donate_author_template, it)
        }
        observe(viewModel.donateDescription) {
            content.text = it
        }
        observe(viewModel.donateProgress) {
            handleProgress(it)
        }
        observe(viewModel.donateDate) {
            handleDate(it)
        }
    }

    private fun handleProgress(it: ProgressInfo) {
        helpButton.isEnabled = it.currentMoney != it.allMoney
        donateProgressTitle.text = when (it.currentMoney) {
            0L -> getString(R.string.donate_first)
            it.allMoney -> getString(
                R.string.donate_complete,
                requireContext().makeCurrencyString(it.allMoney)
            )
            else -> obtainProgressString(it.currentMoney, it.allMoney)
        }
        if (it.currentMoney == it.allMoney) {
            mainProgress.finish(
                getString(
                    R.string.donate_complete,
                    requireContext().makeCurrencyString(it.allMoney)
                )
            )
        }
        donateProgress.progress = it.currentProgress
        mainProgress.setProgress(it.currentProgress / 100f)
        mainProgress.setCurrentDonate(requireContext().makeCurrencyString(it.currentMoney))
        mainProgress.setTotalDonate(requireContext().makeCurrencyString(it.allMoney))
    }

    private fun handleDate(date: DateInfo) {
        expireText.text = if (date.isRegular) {
            getString(R.string.donate_monthly)
        } else {
            obtainExpireText(date.date)
        }
        mainProgress.setTitle(obtainProgressExpireText(date.date))
    }

    private fun obtainProgressExpireText(date: String?): String {
        return getString(R.string.expire_progress_regular, "сентябре") //todo
    }

    private fun loadPhoto(uri: Uri?) {
        Glide.with(requireContext())
            .load(uri)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(picture)
    }

    private fun obtainProgressString(currentMoney: Long, allMoney: Long?): String {
        val firstCurrency = requireContext().makeCurrencyString(currentMoney)
        val secondCurrency = requireContext().makeCurrencyString(allMoney)
        return getString(R.string.donate_progress, firstCurrency, secondCurrency)
    }

    private fun obtainExpireText(date: String?): String {
        return getString(R.string.donate_monthly) //todo
    }

    private fun handleView() {
        buttonBack.setOnClickListener {
            findNavController().navigateUp()
        }
        helpButton.setOnClickListener {
            viewModel.onHelpClicked()
        }
    }
}
