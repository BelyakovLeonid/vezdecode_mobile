package com.belyakov.vezdecode.presentation.start

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.utils.getStyleAttrs
import kotlinx.android.synthetic.main.v_donate_type.view.*

class DonateTypeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.v_donate_type, this)
        getStyleAttrs(attrs, R.styleable.DonateTypeView, defStyleAttr){
            icon.setImageResource(it.getResourceId(R.styleable.DonateTypeView_icon, 0))
            title.text = it.getString(R.styleable.DonateTypeView_title)
            subtitle.text = it.getString(R.styleable.DonateTypeView_subTitle)
        }
    }
}