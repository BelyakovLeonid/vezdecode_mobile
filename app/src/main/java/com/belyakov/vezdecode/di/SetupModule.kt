package com.belyakov.vezdecode.di

import com.belyakov.vezdecode.data.DonateRepository
import com.belyakov.vezdecode.data.DonateRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SetupModule {
    @Singleton
    @Provides
    fun provideDonateRepository(): DonateRepository = DonateRepositoryImpl()
}