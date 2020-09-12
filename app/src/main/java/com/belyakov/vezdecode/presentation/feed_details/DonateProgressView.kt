package com.belyakov.vezdecode.presentation.feed_details

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.belyakov.vezdecode.R
import com.belyakov.vezdecode.utils.dpToPx
import kotlin.math.min

private const val CORNERS_RADIUS = 6
private const val PROGRESS_HEIGHT = 36
private const val TEXT_MARGIN = 8

class DonateProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var currentProgress: Float = 0.0f
    private val isFinished
        get() = currentProgress == 1f

    private var titleText: String = ""
    private var finishText: String = ""
    private var currentDonateText: String = ""
    private var totalDonateText: String = ""

    private val titleTextBounds = Rect()
    private val finishTextBounds = Rect()
    private val currentTextBounds = Rect()
    private val totalTextBounds = Rect()

    private val greenRectangle = RectF()
    private val grayRectangle = RectF()

    private val isCurrentTextInside
        get() = progressWidth > currentTextWidth

    private val isTotalTextInside
        get() = if (isCurrentTextInside) {
            (width - progressWidth) > currentTextWidth
        } else {
            (width - (progressWidth + currentTextWidth)) > currentTextWidth
        }

    private val titleTextHeight
        get() = titleTextBounds.height() + dpToPx(TEXT_MARGIN)

    private val currentTextWidth
        get() = currentTextBounds.width() + dpToPx(TEXT_MARGIN)

    private val totalTextWidth
        get() = totalTextBounds.width() + dpToPx(TEXT_MARGIN)

    private val progressWidth
        get() = currentProgress * width

    private var textYPosition: Float = 0f
    private var textXPosition: Float = 0f

    private val boldTypeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textSize = dpToPx(14)
    }

    private val colorBlack = ContextCompat.getColor(context, R.color.colorBlack)
    private val colorGray = ContextCompat.getColor(context, R.color.colorProgressGray)
    private val colorGrayDark = ContextCompat.getColor(context, R.color.colorGray)
    private val colorGreen = ContextCompat.getColor(context, R.color.colorProgressGreen)
    private val colorWhile = ContextCompat.getColor(context, R.color.colorWhite)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        grayRectangle.set(0f, titleTextHeight, w.toFloat(), h.toFloat())
        greenRectangle.set(0f, titleTextHeight, (currentProgress * width), height.toFloat())
        textYPosition = h - (dpToPx(PROGRESS_HEIGHT) - totalTextBounds.height()) / 2
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredHeight = titleTextHeight + dpToPx(PROGRESS_HEIGHT) + paddingTop + paddingBottom

        setMeasuredDimension(
            widthMeasureSpec,
            measureDimension(desiredHeight.toInt(), heightMeasureSpec)
        )
    }

    private fun measureDimension(desiredSize: Int, measureSpec: Int): Int {
        var result: Int
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            result = desiredSize
            if (specMode == MeasureSpec.AT_MOST) {
                result = min(result, specSize)
            }
        }
        return result
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawTitle(canvas)
        drawGrayRectangle(canvas)
        drawGreenRectangle(canvas)
        if (isFinished) {
            drawFinishText(canvas)
        } else {
            drawCurrentDonateText(canvas)
            drawTotalDonateText(canvas)
        }
    }

    private fun drawTitle(canvas: Canvas?) {
        paint.typeface = null
        paint.color = colorBlack
        canvas?.drawText(titleText, 0f, titleTextBounds.height().toFloat(), paint)
    }

    private fun drawGrayRectangle(canvas: Canvas?) {
        paint.color = colorGray
        canvas?.drawRoundRect(grayRectangle, dpToPx(CORNERS_RADIUS), dpToPx(CORNERS_RADIUS), paint)
    }

    private fun drawGreenRectangle(canvas: Canvas?) {
        paint.color = colorGreen
        canvas?.drawRoundRect(greenRectangle, dpToPx(CORNERS_RADIUS), dpToPx(CORNERS_RADIUS), paint)
    }

    private fun drawCurrentDonateText(canvas: Canvas?) {
        paint.typeface = boldTypeface
        textYPosition = height - (dpToPx(PROGRESS_HEIGHT) - totalTextBounds.height()) / 2
        if (isCurrentTextInside) {
            textXPosition = progressWidth - currentTextWidth
            paint.color = colorWhile
        } else {
            textXPosition = progressWidth + dpToPx(TEXT_MARGIN)
            paint.color = colorGreen
        }

        canvas?.drawText(
            currentDonateText,
            textXPosition,
            textYPosition,
            paint
        )
    }

    private fun drawTotalDonateText(canvas: Canvas?) {
        paint.typeface = boldTypeface
        paint.color = colorGrayDark
        textXPosition = width - totalTextWidth
        if (!isTotalTextInside) {
            textYPosition = height - dpToPx(PROGRESS_HEIGHT) - dpToPx(TEXT_MARGIN)
        }

        canvas?.drawText(
            totalDonateText,
            textXPosition,
            textYPosition,
            paint
        )
    }

    private fun drawFinishText(canvas: Canvas?) {
        paint.typeface = boldTypeface
        paint.color = colorWhile
        textXPosition = (width - finishTextBounds.width().toFloat()) / 2
        textYPosition = height - (dpToPx(PROGRESS_HEIGHT) - totalTextBounds.height()) / 2

        canvas?.drawText(
            finishText,
            textXPosition,
            textYPosition,
            paint
        )
    }

    fun setTitle(title: String) {
        titleText = title
        paint.getTextBounds(titleText, 0, titleText.length, titleTextBounds)
        invalidate()
    }

    fun setCurrentDonate(currentDonate: String) {
        currentDonateText = currentDonate
        paint.getTextBounds(currentDonateText, 0, currentDonateText.length, currentTextBounds)
        invalidate()
    }

    fun setTotalDonate(totalDonate: String) {
        totalDonateText = totalDonate
        paint.getTextBounds(totalDonateText, 0, totalDonateText.length, totalTextBounds)
        invalidate()
    }

    fun setProgress(progress: Float) {
        currentProgress = progress
        greenRectangle.set(0f, titleTextHeight, (currentProgress * width), height.toFloat())
        invalidate()
    }

    fun finish(finishText: String) {
        this.finishText = finishText
        paint.getTextBounds(finishText, 0, finishText.length, finishTextBounds)
        invalidate()
    }
}