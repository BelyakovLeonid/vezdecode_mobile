package com.belyakov.vezdecode.presentation.setup_details

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.belyakov.vezdecode.data.DonateRepository
import com.belyakov.vezdecode.presentation.feed.DateInfo
import com.belyakov.vezdecode.presentation.feed.ProgressInfo
import javax.inject.Inject

class SetupDetailsViewModel @Inject constructor(
    private val repository: DonateRepository
) : ViewModel() {
    private val _donateImage = MutableLiveData<Uri?>()
    val donateImage: LiveData<Uri?> = _donateImage

    private val _donateName = MutableLiveData<String?>()
    val donateName: LiveData<String?> = _donateName

    private val _donateAuthor = MutableLiveData<String?>()
    val donateAuthor: LiveData<String?> = _donateAuthor

    private val _donateDate = MutableLiveData<DateInfo>()
    val donateDate: LiveData<DateInfo> = _donateDate

    init {
        val donate = repository.getDonate()
        _donateImage.value = donate.imageUri
        _donateName.value = donate.name
        _donateAuthor.value = donate.author
        _donateDate.value = DateInfo(donate.isRegular, donate.expireDate)
    }

    fun saveFeedDescription(desc: String?) {
        repository.setFeedDescription(desc)
    }
}