package com.belyakov.vezdecode.data

import android.net.Uri
import com.belyakov.vezdecode.domain.Donate
import com.belyakov.vezdecode.presentation.start.DonateType

interface DonateRepository {
    fun getDonate(): Donate
    fun setDonateType(type: DonateType)
    fun setDonateImage(uri: Uri?)
    fun setDonateName(name: String?)
    fun setDonateSum(sum: Long?)
    fun setDonateCurrentSum(sum: Long)
    fun setDonateGoal(goal: String?)
    fun setDonateDescription(desc: String?)
    fun setFeedDescription(desc: String?)
    fun setDonateExpireByDate(byDate: Boolean?)
    fun setDonateDate(date: String?)
    fun setDonateWallet(wallet: String?)
    fun addMoney()
    fun clearSetupProgress()
    fun setDonateAuthor(author: String?)
}
