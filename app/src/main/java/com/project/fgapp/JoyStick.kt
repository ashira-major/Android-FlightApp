package com.project.joystick

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.Integer.min
import kotlin.math.pow
import kotlin.math.sqrt

class JoyStick @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : View(context, attrs, defStyleAttr) {

    // base color
    private val paint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.argb(220, 102, 178, 255)
        isAntiAlias = true
    }

    // joystick color
    private val paintJoy = Paint().apply {
        style = Paint.Style.FILL
        color = Color.argb(250, 153, 204, 255)
        isAntiAlias = true
    }
    private var sin: Float = 0f
    private var cos: Float = 0f
    private val ratio: Int = 20
    private var radius: Float = 0f
    private var center: PointF = PointF()
    private var joy_rad: Float = 0f
    private var d : Float = 0f
    private var joy_center: PointF = PointF()
    lateinit var OnChangeJ: (Float, Float) -> Unit

    override fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        radius = 0.45f * kotlin.math.min(width, height).toFloat()
        center = PointF(width/2.0f, height/2.0f)
        joy_center = PointF(width/2.0f, height/2.0f)
        joy_rad = 0.2f * kotlin.math.min(width, height).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // big circle
        canvas.drawCircle(center.x, center.y, radius, paint)
        var colors = Paint()

        for (i in 1..(radius/ratio).toInt()) {
            colors.setARGB(150/i, 255, 0, 0)
            canvas.drawCircle(joy_center.x - cos * d * (ratio/radius) * i,
                joy_center.y - sin * d * (ratio/radius) * i, i * (ratio * joy_rad / radius), colors)
        }
        // joy stick circle
        canvas.drawCircle(joy_center.x, joy_center.y, joy_rad, paintJoy)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return true
        }
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> touchMove(event.x, event.y)
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
            MotionEvent.ACTION_UP -> touchUp(event.x, event.y)
        }
        return true
    }

    private fun touchMove(x: Float, y: Float) {
        d = sqrt((center.x - x).pow(2) + (center.y - y).pow(2))
        if (d > 0) {
            cos = (x - center.x) / d
            sin = (y - center.y) / d
        }
        if (d <= radius) {
            joy_center.x = x
            joy_center.y = y
            var e = (y - center.y) / radius
            var a = (x - center.x) / radius
            OnChangeJ(e , a)
        } else {
            var newX = center.x + (x - center.x) * (radius / d)
            var newY = center.y + (y - center.y) * (radius / d)
            joy_center.x = newX
            joy_center.y = newY
            var e = (newY - center.y) / radius
            var a = (newX - center.x) / radius
            OnChangeJ(e , a)
        }
        invalidate()
    }

    private fun touchUp(x: Float, y: Float) {
        d = sqrt((center.x - x).pow(2) + (center.y - y).pow(2))
        if (d <= radius) {
            joy_center.x = x
            joy_center.y = y
        } else {
            var newX = center.x + (x - center.x) * (radius / d)
            var newY = center.y + (y - center.y) * (radius / d)
            joy_center.x = newX
            joy_center.y = newY
        }
        invalidate()
    }

    fun nightColor() {
        paint.color = Color.argb(220, 0, 51, 102)
        paintJoy.color = Color.argb(230, 0, 0, 102)
        touchUp(joy_center.x, joy_center.y)
    }

    fun dayColor() {
        paint.color = Color.argb(220, 102, 178, 255)
        paintJoy.color = Color.argb(250, 153, 204, 255)
        touchUp(joy_center.x, joy_center.y)
    }
}