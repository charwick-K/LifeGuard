package com.example.healthsuite.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeDetector(
    context: Context,
    private val shakeThreshold: Float = 14f,
    private val windowMs: Long = 500
) : SensorEventListener {
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private var lastShakeTime = 0L
    var onShake: (() -> Unit)? = null

    fun start() { sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI) }
    fun stop() { sensorManager.unregisterListener(this) }

    override fun onSensorChanged(event: SensorEvent) {
        val g = sqrt(event.values[0]*event.values[0] + event.values[1]*event.values[1] + event.values[2]*event.values[2])
        val now = System.currentTimeMillis()
        if (g > shakeThreshold && now - lastShakeTime > windowMs) {
            lastShakeTime = now
            onShake?.invoke()
        }
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
