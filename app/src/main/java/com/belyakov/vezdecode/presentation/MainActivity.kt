package com.belyakov.vezdecode.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.updatePadding
import com.belyakov.vezdecode.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupInsets()
    }

    private fun setupInsets() {
        root.run {
            setOnApplyWindowInsetsListener { _, windowInsets ->
                root.updatePadding(bottom = windowInsets.systemWindowInsetBottom)
                windowInsets
            }
            requestApplyInsets()
        }

    }

    override fun onStart() {
        super.onStart()
        setFullScreenMode()
    }

    @Suppress("DEPRECATION")
    private fun setFullScreenMode() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }
}