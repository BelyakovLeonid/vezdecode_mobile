package com.belyakov.vezdecode.data

import android.net.Uri
import com.belyakov.vezdecode.domain.Donate
import com.belyakov.vezdecode.presentation.start.DonateType

class DonateRepositoryImpl : DonateRepository {
    private var donate: Donate = Donate()

    override fun getDonate(): Donate = donate.copy()

    override fun setDonateType(type: DonateType) {
        donate.isRegular = type == DonateType.REGULAR
    }

    override fun setDonateImage(uri: Uri?) {
        donate.imageUri = uri
    }

    override fun setDonateName(name: String?) {
        donate.name = name
    }

    override fun setDonateSum(sum: Long?) {
        donate.sum = sum
    }

    override fun setDonateCurrentSum(sum: Long) {
        donate.currentSum = sum
    }

    override fun setDonateGoal(goal: String?) {
        donate.goal = goal
    }

    override fun setDonateExpireByDate(byDate: Boolean?) {

    }

    override fun setDonateDescription(desc: String?) {
        donate.description = desc
    }

    override fun setFeedDescription(desc: String?) {
        donate.feedDescription = desc
    }

    override fun setDonateDate(date: String?) {
        donate.expireDate = date
    }

    override fun setDonateWallet(wallet: String?) {
        donate.wallet
    }

    override fun setDonateAuthor(author: String?) {
        donate.author = author
    }

    override fun clearSetupProgress() {
        donate = Donate()
    }

    override fun addMoney() {
        val currentMoney = donate.currentSum
        val allMoney = donate.sum ?: 0
        val step = allMoney.div(10)
        donate.currentSum = if (currentMoney + step > allMoney){
            allMoney
        }else{
            currentMoney + step
        }
    }
}