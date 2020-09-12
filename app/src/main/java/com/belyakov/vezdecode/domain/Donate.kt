package com.belyakov.vezdecode.domain

import android.net.Uri

const val ONLY_ID_FOR_NOW = 1L

data class Donate(
    val id: Long = ONLY_ID_FOR_NOW,
    var isRegular: Boolean = true,
    var imageUri: Uri? = null,
    var author: String? = null,
    var name: String? = null,
    var currentSum: Long = 0,
    var sum: Long? = null,
    var goal: String? = null,
    var description: String? = null,
    var feedDescription: String? = null,
    var wallet: String? = null,
    var expireDate: String? = null
) {
    fun getProgress(): Int {
        return ((currentSum.toDouble() / (sum ?: 0)) * 100).toInt()
    }
}