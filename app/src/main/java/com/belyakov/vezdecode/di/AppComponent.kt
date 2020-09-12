package com.belyakov.vezdecode.di

import com.belyakov.vezdecode.presentation.feed.FeedFragment
import com.belyakov.vezdecode.presentation.feed_details.FeedDetailsFragment
import com.belyakov.vezdecode.presentation.setup.regular.RegularSetupFragment
import com.belyakov.vezdecode.presentation.setup.target.SetupTargetFragment
import com.belyakov.vezdecode.presentation.setup_details.SetupDetailsFragment
import com.belyakov.vezdecode.presentation.setup_extra.SetupExtraFragment
import com.belyakov.vezdecode.presentation.start.StartFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        SetupModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: SetupExtraFragment)
    fun inject(fragment: SetupTargetFragment)
    fun inject(fragment: StartFragment)
    fun inject(fragment: FeedFragment)
    fun inject(fragment: FeedDetailsFragment)
    fun inject(fragment: SetupDetailsFragment)
    fun inject(fragment: RegularSetupFragment)
    fun inject(factory: ViewModelFactory)

    @Component.Factory
    interface Factory {
        fun create(): AppComponent
    }
}