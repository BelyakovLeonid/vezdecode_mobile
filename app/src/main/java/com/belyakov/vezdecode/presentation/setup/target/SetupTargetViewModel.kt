package com.belyakov.vezdecode.presentation.setup.target

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belyakov.vezdecode.data.DonateRepository
import javax.inject.Inject

class SetupTargetViewModel @Inject constructor(
    private val repository: DonateRepository
) : ViewModel() {

    private val _editIsComplete = MutableLiveData<Boolean>()
    val editIsComplete: LiveData<Boolean> = _editIsComplete

    private var donatePhotoUri: Uri? = null
    private var donateName: String? = null
    private var donateSum: String? = null
    private var donateGoal: String? = null
    private var donateDescription: String? = null

    private val isComplete: Boolean
        get() = !donateName.isNullOrEmpty()
                && !donateSum.isNullOrEmpty()
                && !donateGoal.isNullOrEmpty()
                && !donateDescription.isNullOrEmpty()
                && donatePhotoUri != null

    fun setPhotoUri(uri: Uri?) {
        donatePhotoUri = uri
        repository.setDonateImage(uri)
        _editIsComplete.value = isComplete
    }

    fun setDonateName(name: String?) {
        donateName = name
        repository.setDonateName(name)
        _editIsComplete.value = isComplete
    }

    fun setDonateSum(sum: String?) {
        donateSum = sum
        repository.setDonateSum(sum?.toLong())
        _editIsComplete.value = isComplete
    }

    fun setDonateGoal(goal: String?) {
        donateGoal = goal
        repository.setDonateGoal(goal)
        _editIsComplete.value = isComplete
    }

    fun setDonateDescription(description: String?) {
        donateDescription = description
        repository.setDonateDescription(description)
        _editIsComplete.value = isComplete
    }
}