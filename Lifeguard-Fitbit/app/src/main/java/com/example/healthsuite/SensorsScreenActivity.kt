package com.example.healthsuite

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthsuite.sensors.FallDetector
import com.example.healthsuite.sensors.ShakeDetector

class SensorsScreenActivity : AppCompatActivity() {
    private lateinit var shakeDetector: ShakeDetector
    private lateinit var fallDetector: FallDetector
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors_screen)
        val statusTv = findViewById<TextView>(R.id.statusTv)

        shakeDetector = ShakeDetector(this).apply {
            onShake = {
                runOnUiThread {
                    statusTv.text = "Shake detected — SOS"
                    // TODO: integrate SMS / notification / backend alert
                }
            }
        }

        fallDetector = FallDetector(this).apply {
            onFall = {
                runOnUiThread {
                    statusTv.text = "Fall detected — Alert sent"
                    // TODO: send alert to server/contacts
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        shakeDetector.start()
        fallDetector.start()
    }

    override fun onPause() {
        super.onPause()
        shakeDetector.stop()
        fallDetector.stop()
    }
}
