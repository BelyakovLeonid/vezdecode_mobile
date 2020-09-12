package com.belyakov.vezdecode.presentation.start

import androidx.lifecycle.ViewModel
import com.belyakov.vezdecode.data.DonateRepository
import javax.inject.Inject

class StartViewModel @Inject constructor(
    private val repository: DonateRepository
) : ViewModel() {

    fun setDonateType(type: DonateType){
        repository.setDonateType(type)
    }
}

enum class DonateType {
    REGULAR, TARGET
}