package com.example.healthsuite

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.healthsuite.db.AppDatabase
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.coroutines.*

class DashboardActivity : AppCompatActivity() {
    private val uiScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val chart = findViewById<LineChart>(R.id.lineChart)
        val statsTv = findViewById<TextView>(R.id.statsTv)

        // sample chart (synthetic HR)
        val entries = mutableListOf<Entry>()
        for (i in 0..59) {
            val hr = 70 + (Math.sin(i / 5.0) * 10).toFloat()
            entries.add(Entry(i.toFloat(), hr))
        }
        val set = LineDataSet(entries, "Heart Rate (sample)")
        chart.data = LineData(set)
        chart.invalidate()

        // load recent events from DB and show counts
        uiScope.launch {
            val db = AppDatabase.getDb(this@DashboardActivity)
            val events = withContext(Dispatchers.IO) { db.eventDao().recent() }
            val shakes = events.count { it.type == "shake" }
            val falls = events.count { it.type == "fall" }
            statsTv.text = "Recent events: Shakes=$shakes, Falls=$falls (showing latest ${events.size})"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        uiScope.cancel()
    }
}
