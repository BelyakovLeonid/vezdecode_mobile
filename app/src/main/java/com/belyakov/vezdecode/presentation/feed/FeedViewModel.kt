package com.belyakov.vezdecode.presentation.feed

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belyakov.vezdecode.data.DonateRepository
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val repository: DonateRepository
) : ViewModel() {
    private val _donateImage = MutableLiveData<Uri?>()
    val donateImage: LiveData<Uri?> = _donateImage

    private val _donateName = MutableLiveData<String?>()
    val donateName: LiveData<String?> = _donateName

    private val _donateAuthor = MutableLiveData<String?>()
    val donateAuthor: LiveData<String?> = _donateAuthor

    private val _donateFeedDescription = MutableLiveData<String?>()
    val donateFeedDescription: LiveData<String?> = _donateFeedDescription

    private val _donateProgress = MutableLiveData<ProgressInfo>()
    val donateProgress: LiveData<ProgressInfo> = _donateProgress

    private val _donateDate = MutableLiveData<DateInfo>()
    val donateDate: LiveData<DateInfo> = _donateDate

    private fun refreshDonateInfo() {
        val donate = repository.getDonate()
        _donateImage.value = donate.imageUri
        _donateName.value = donate.name
        _donateAuthor.value = donate.author
        _donateFeedDescription.value = donate.feedDescription
        _donateDate.value = DateInfo(donate.isRegular, donate.expireDate)
        _donateProgress.value = ProgressInfo(donate.currentSum, donate.sum, donate.getProgress())
    }

    fun onHelpClicked() {
        repository.addMoney()
        refreshDonateInfo()
    }

    fun onCreate() {
        refreshDonateInfo()
    }

    fun clearData() {
        repository.clearSetupProgress()
    }
}

data class ProgressInfo(
    val currentMoney: Long,
    val allMoney: Long?,
    val currentProgress: Int
)

data class DateInfo(
    val isRegular: Boolean,
    val date: String?
)