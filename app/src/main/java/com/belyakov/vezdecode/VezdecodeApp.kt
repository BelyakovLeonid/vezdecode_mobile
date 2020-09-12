package com.belyakov.vezdecode

import android.app.Application
import com.belyakov.vezdecode.di.DaggerAppComponent

class VezdecodeApp: Application() {
    companion object {
        var appComponent = DaggerAppComponent.factory().create()
    }
}