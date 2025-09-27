package com.example.healthsuite

import android.content.Context
import android.util.Log

// NOTE: This is a helper stub for Health Connect integration.
// Health Connect requires adding the SDK, asking permissions, and using HealthConnectClient.
// See official docs: https://developer.android.com/guide/health-and-fitness/health-connect
class HealthConnectHelper(private val context: Context) {
    fun requestPermissions() {
        // TODO: implement Health Connect permission flow using HealthConnectClient
        Log.d("HealthConnect", "Request permissions - implement in app")
    }

    suspend fun readLatestHeartRate(): Double? {
        // TODO: use HealthConnectClient.readRecords for HeartRate records
        return null
    }
}
