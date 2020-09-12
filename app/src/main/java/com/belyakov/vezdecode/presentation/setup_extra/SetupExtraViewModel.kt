package com.belyakov.vezdecode.presentation.setup_extra

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belyakov.vezdecode.data.DonateRepository
import javax.inject.Inject

const val NOT_SET = -1L

class SetupExtraViewModel @Inject constructor(
    private val repository: DonateRepository
) : ViewModel() {
    private var author: String? = null
    private var byDate: Boolean = true
    private var timeInMillis: Long = NOT_SET

    private val isComplete: Boolean
        get() = (!byDate || timeInMillis > 0)
                && !author.isNullOrEmpty()

    private val _editIsComplete = MutableLiveData<Boolean>()
    val editIsComplete: LiveData<Boolean> = _editIsComplete

    fun setExpireByDate(byDate: Boolean) {
        this.byDate = byDate
        repository.setDonateExpireByDate(byDate)
        _editIsComplete.value = isComplete
    }

    fun setExpireDate(timeInMillis: Long) {
        this.timeInMillis = timeInMillis
        repository.setDonateDate(timeInMillis.toString()) //todo
        _editIsComplete.value = isComplete
    }

    fun setAuthor(author: String) {
        this.author = author
        repository.setDonateAuthor(author)
        _editIsComplete.value = isComplete
    }
}