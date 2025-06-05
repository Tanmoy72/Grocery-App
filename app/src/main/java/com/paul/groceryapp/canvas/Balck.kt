package com.paul.groceryapp.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.View
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


class BlackAnalogClockView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val handler = Handler(Looper.getMainLooper())
    private val calendar = Calendar.getInstance()

    private val updateRunnable = object : Runnable {
        override fun run() {
            invalidate()
            handler.postDelayed(this, 1000)
        }
    }

    init {
        handler.post(updateRunnable)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val radius = (min(width, height) / 2) - 16
        val centerX = width / 2
        val centerY = height / 2

        // Draw black background
        canvas.drawColor(Color.BLACK)

        // Draw minute ticks
        for (i in 0 until 60) {
            val angle = Math.toRadians((i * 6 - 90).toDouble())
            val startR = if (i % 5 == 0) radius * 0.92 else radius * 0.96
            val endR = radius
            val startX = (centerX + cos(angle) * startR).toFloat()
            val startY = (centerY + sin(angle) * startR).toFloat()
            val endX = (centerX + cos(angle) * endR).toFloat()
            val endY = (centerY + sin(angle) * endR).toFloat()

            paint.color = Color.CYAN
            paint.strokeWidth = if (i % 5 == 0) 4f else 2f
            canvas.drawLine(startX, startY, endX, endY, paint)
        }

        // Draw numbers (1 to 12)
        paint.color = Color.CYAN
        paint.textSize = 42f
        paint.textAlign = Paint.Align.CENTER
        for (i in 1..12) {
            val angle = Math.toRadians((i * 30 - 90).toDouble())
            val numberR = radius * 0.78
            val x = (centerX + cos(angle) * numberR).toFloat()
            val y = (centerY + sin(angle) * numberR + 14).toFloat()
            canvas.drawText(i.toString(), x, y, paint)
        }

        // Get current time
        calendar.timeInMillis = System.currentTimeMillis()
        val hour = calendar.get(Calendar.HOUR)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)

        // Draw hour hand
        paint.color = Color.CYAN
        paint.strokeWidth = 12f
        val hourAngle = Math.toRadians((hour + minute / 60.0) * 30 - 90)
        canvas.drawLine(
            centerX, centerY,
            (centerX + cos(hourAngle) * radius * 0.5).toFloat(),
            (centerY + sin(hourAngle) * radius * 0.5).toFloat(),
            paint
        )

        // Draw minute hand
        paint.strokeWidth = 8f
        val minuteAngle = Math.toRadians((minute + second / 60.0) * 6 - 90)
        canvas.drawLine(
            centerX, centerY,
            (centerX + cos(minuteAngle) * radius * 0.7).toFloat(),
            (centerY + sin(minuteAngle) * radius * 0.7).toFloat(),
            paint
        )

        // Draw second hand
        paint.color = Color.LTGRAY
        paint.strokeWidth = 4f
        val secondAngle = Math.toRadians(second * 6.0 - 90)
        canvas.drawLine(
            centerX, centerY,
            (centerX + cos(secondAngle) * radius * 0.85).toFloat(),
            (centerY + sin(secondAngle) * radius * 0.85).toFloat(),
            paint
        )

        // Draw center dot
        paint.style = Paint.Style.FILL
        paint.color = Color.CYAN
        canvas.drawCircle(centerX, centerY, 12f, paint)

        // Draw "STYLESEVEN" text
        paint.color = Color.MAGENTA
        paint.textSize = 36f
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("STYLESEVEN", centerX, centerY - radius * 0.6f, paint)

        // Draw day, dayOfYear, month
        paint.textSize = 32f
        val day = SimpleDateFormat("EEE", Locale.getDefault()).format(calendar.time)
        val month = SimpleDateFormat("MMM", Locale.getDefault()).format(calendar.time)
        val dayOfYear = calendar.get(Calendar.DAY_OF_YEAR).toString()
        val date = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))

        paint.textAlign = Paint.Align.RIGHT
        canvas.drawText(day.uppercase(), centerX - 50f, centerY + radius * 0.4f, paint)

        paint.textAlign = Paint.Align.LEFT
        canvas.drawText(month.uppercase(), centerX + 50f, centerY + radius * 0.4f, paint)

        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(dayOfYear, centerX, centerY + radius * 0.4f, paint)

        // Draw boxed date (e.g., 01)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 3f
        paint.color = Color.MAGENTA
        val boxWidth = 80f
        val boxHeight = 60f
        val boxLeft = centerX + radius * 0.5f
        val boxTop = centerY - 20f
        val boxRight = boxLeft + boxWidth
        val boxBottom = boxTop + boxHeight
        canvas.drawRect(boxLeft, boxTop, boxRight, boxBottom, paint)

        // Draw date inside box
        paint.style = Paint.Style.FILL
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(date, boxLeft + boxWidth / 2, boxTop + boxHeight / 1.7f, paint)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        handler.removeCallbacks(updateRunnable)
    }
}
