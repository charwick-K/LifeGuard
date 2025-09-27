package com.example.healthsuite

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SensorsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensors)
        val sensorsBtn = findViewById<Button>(R.id.btnSensors)
        val dashboardBtn = findViewById<Button>(R.id.btnDashboard)
        val fitbitBtn = findViewById<Button>(R.id.btnFitbit)

        sensorsBtn.setOnClickListener {
            startActivity(Intent(this, SensorsScreenActivity::class.java))
        }
        dashboardBtn.setOnClickListener {
            startActivity(Intent(this, DashboardActivity::class.java))
        }
        fitbitBtn.setOnClickListener {
            startActivity(Intent(this, FitbitAuthActivity::class.java))
        }
    }
}
