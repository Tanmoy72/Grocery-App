package com.paul.groceryapp.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.sin

class AnalogClockView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint()
    private val handler = Handler(Looper.getMainLooper())

    private val updateRunnable = object : Runnable {
        override fun run() {
            invalidate() // Redraw
            handler.postDelayed(this, 1000) // Update every second
        }
    }

    init {
        handler.post(updateRunnable)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = (Math.min(width, height) / 2) - 16f
        val centerX = width / 2
        val centerY = height / 2

        paint.isAntiAlias = true

        // Draw Clock Circle
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 8f
        paint.color = Color.BLACK
        canvas.drawCircle(centerX, centerY, radius, paint)

        // Get Current Time
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        // Angles in radians
        val hourAngle = Math.toRadians((hour + minute / 60.0) * 30 - 90)
        val minuteAngle = Math.toRadians((minute + second / 60.0) * 6 - 90)
        val secondAngle = Math.toRadians(second * 6.0 - 90)


        val frameRunnable = object : Runnable {
            override fun run() {
                invalidate()
                postOnAnimation(this) // smoother than postDelayed
            }
        }
        post(frameRunnable)


        // Draw Hour Hand
        paint.color = Color.BLACK
        paint.strokeWidth = 18f
        canvas.drawLine(
            centerX,
            centerY,
            (centerX + cos(hourAngle) * radius * 0.5).toFloat(),
            (centerY + sin(hourAngle) * radius * 0.5).toFloat(),
            paint
        )

        // Draw Minute Hand
        paint.strokeWidth = 12f
        canvas.drawLine(
            centerX,
            centerY,
            (centerX + cos(minuteAngle) * radius * 0.7).toFloat(),
            (centerY + sin(minuteAngle) * radius * 0.7).toFloat(),
            paint
        )

        // Draw Second Hand
        paint.color = Color.RED
        paint.strokeWidth = 7f
        canvas.drawLine(
            centerX,
            centerY,
            (centerX + cos(secondAngle) * radius * 0.9).toFloat(),
            (centerY + sin(secondAngle) * radius * 0.9).toFloat(),
            paint
        )

        // Center dot
        paint.style = Paint.Style.FILL
        paint.color = Color.BLACK
        canvas.drawCircle(centerX, centerY, 12f, paint)

        // Draw numbers 1 to 12
        paint.color = Color.BLACK
        paint.textSize = 50f
        paint.textAlign = Paint.Align.CENTER

        for (i in 1..12) {
            val angle = Math.toRadians((i * 30 - 90).toDouble()) // 30° per hour, offset by -90°
            val numberRadius = radius * 0.85
            val x = (centerX + cos(angle) * numberRadius).toFloat()
            val y = (centerY + sin(angle) * numberRadius + 15).toFloat() // Adjust vertical offset for centering text
            canvas.drawText(i.toString(), x, y, paint)
        }

        // Minute and hour ticks
        for (i in 0 until 60) {
            val angle = Math.toRadians((i * 6 - 90).toDouble())
            val startRadius = if (i % 5 == 0) radius * 0.90 else radius * 0.95
            val endRadius = radius

            val startX = (centerX + cos(angle) * startRadius).toFloat()
            val startY = (centerY + sin(angle) * startRadius).toFloat()
            val endX = (centerX + cos(angle) * endRadius).toFloat()
            val endY = (centerY + sin(angle) * endRadius).toFloat()

            paint.strokeWidth = if (i % 5 == 0) 6f else 2f
            paint.color = Color.BLACK
            canvas.drawLine(startX, startY, endX, endY, paint)

            val dotX = (centerX + cos(angle) * radius * 0.97).toFloat()
            val dotY = (centerY + sin(angle) * radius * 0.97).toFloat()

            paint.style = Paint.Style.FILL
            paint.strokeWidth = 1f
            canvas.drawCircle(dotX, dotY, if (i % 5 == 0) 6f else 2f, paint)


        }




    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(updateRunnable)
    }
}
