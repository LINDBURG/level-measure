package com.linbug.level

import android.content.Context
import android.hardware.SensorEvent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.constraintlayout.widget.ConstraintLayout
import com.linbug.level.databinding.ViewTiltBinding


class TiltView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
    ) : ConstraintLayout(context, attrs, defStyle) {
    private var binding: ViewTiltBinding

    private var centerAnim: Animation? = null
    private var horizontalAnim: Animation? = null
    private var verticalAnim: Animation? = null

    private var oldX: Float = 0f
    private var oldY: Float = 0f
    private var xCoord: Float = 0f
    private var yCoord: Float = 0f

    init {
        val layoutInflater = LayoutInflater.from(context)
        binding = ViewTiltBinding.inflate(layoutInflater, this, true)

    }


    private fun updateView() {
        centerAnim = TranslateAnimation(oldX * 12, xCoord * 12 , -oldY * 12, -yCoord * 12 )
        centerAnim?.duration = 100
        centerAnim?.fillAfter = true
        binding.centerBubble.startAnimation(centerAnim)

        horizontalAnim = TranslateAnimation(oldX * 30, xCoord * 30, 0f, 0f )
        horizontalAnim?.duration = 100
        horizontalAnim?.fillAfter = true
        binding.horizontalBubble.startAnimation(horizontalAnim)

        verticalAnim = TranslateAnimation(0f, 0f  , -oldY * 30, -yCoord * 30 )
        verticalAnim?.duration = 100
        verticalAnim?.fillAfter = true
        binding.verticalBubble.startAnimation(verticalAnim)
    }

    fun onSensorEvent( event: SensorEvent) {
        oldX = xCoord
        oldY = yCoord

        xCoord = event.values[0]
        yCoord = event.values[1]
        updateView()
        invalidate()
    }

}