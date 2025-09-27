package com.example.healthsuite.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class FallDetector(context: Context) : SensorEventListener {
    private val sm = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val accel = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    private var freeFallDetectedAt: Long = 0L
    var onFall: (() -> Unit)? = null

    fun start() { sm.registerListener(this, accel, SensorManager.SENSOR_DELAY_GAME) }
    fun stop() { sm.unregisterListener(this) }

    override fun onSensorChanged(event: SensorEvent) {
        val g = sqrt(event.values[0]*event.values[0] + event.values[1]*event.values[1] + event.values[2]*event.values[2]) / SensorManager.GRAVITY_EARTH
        val now = System.currentTimeMillis()
        if (g < 0.5) freeFallDetectedAt = now
        if (freeFallDetectedAt > 0 && now - freeFallDetectedAt < 1200 && g > 2.0) {
            onFall?.invoke()
            freeFallDetectedAt = 0
        }
        if (freeFallDetectedAt > 0 && now - freeFallDetectedAt > 2000) freeFallDetectedAt = 0
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
