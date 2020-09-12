package com.belyakov.vezdecode.di

import androidx.lifecycle.ViewModel
import com.belyakov.vezdecode.presentation.feed.FeedViewModel
import com.belyakov.vezdecode.presentation.feed_details.FeedDetailsFragment
import com.belyakov.vezdecode.presentation.feed_details.FeedDetailsViewModel
import com.belyakov.vezdecode.presentation.setup.regular.RegularSetupViewModel
import com.belyakov.vezdecode.presentation.setup.target.SetupTargetViewModel
import com.belyakov.vezdecode.presentation.setup_details.SetupDetailsViewModel
import com.belyakov.vezdecode.presentation.setup_extra.SetupExtraViewModel
import com.belyakov.vezdecode.presentation.start.StartViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    abstract fun startViewModel(viewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SetupTargetViewModel::class)
    abstract fun setupTargetViewModel(viewModel: SetupTargetViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SetupExtraViewModel::class)
    abstract fun setupExtraViewModel(viewModel: SetupExtraViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedViewModel::class)
    abstract fun feedViewModel(viewModel: FeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedDetailsViewModel::class)
    abstract fun feedDetailsViewModel(viewModel: FeedDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SetupDetailsViewModel::class)
    abstract fun setupDetailsViewModel(viewModel: SetupDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegularSetupViewModel::class)
    abstract fun regularSetupViewModel(viewModel: RegularSetupViewModel): ViewModel
}