package com.belyakov.vezdecode.presentation.setup

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.belyakov.vezdecode.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.v_load_photo.view.*


class LoadPhotoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var onRemovePhotoListener: (()->Unit)? = null

    init {
        View.inflate(context, R.layout.v_load_photo, this)
        buttonClose.setOnClickListener {
            removePhoto()
        }
    }

    private fun removePhoto() {
        onRemovePhotoListener?.invoke()
        photoContainer.setImageURI(null)
        photoGroup.isVisible = false
        loadGroup.isVisible = true
    }

    fun loadPhoto(uri: Uri?) {
        val radius = context.resources.getDimensionPixelSize(R.dimen.corner_radius)

        Glide
            .with(context)
            .load(uri)
            .transform(MultiTransformation(CenterCrop(), RoundedCorners(radius)))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(photoContainer)

        photoGroup.isVisible = true
        loadGroup.isVisible = false
    }
}