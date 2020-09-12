package com.belyakov.vezdecode.presentation.setup

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.utils.getStyleAttrs
import kotlinx.android.synthetic.main.v_setup_expandable.view.*

class SetupExpandableView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var onClickListener: (() -> Unit)? = null

    init {
        View.inflate(context, R.layout.v_setup_expandable, this)
        getStyleAttrs(attrs, R.styleable.SetupExpandableView, defStyleAttr) {
            text.text = it.getString(R.styleable.SetupExpandableView_hint)
            title.text = it.getString(R.styleable.SetupExpandableView_title)
        }
        viewRoot.setOnClickListener {
            onClickListener?.invoke()
        }
    }

    fun setText(newText: String?) {
        text.text = newText
    }
}