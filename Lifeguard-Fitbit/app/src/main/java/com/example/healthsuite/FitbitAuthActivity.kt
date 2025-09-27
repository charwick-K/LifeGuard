package com.example.healthsuite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FitbitAuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fitbit)
        val infoTv = findViewById<TextView>(R.id.infoTv)
        infoTv.text = "This will open Fitbit auth in a browser. After authorizing, Fitbit will redirect to the app's URI. You must implement a backend to exchange the code for tokens (do not embed client secret in app)."

        // Build authorization URL
        val clientId = getString(R.string.fitbit_client_id)
        val redirect = getString(R.string.fitbit_redirect_uri)
        val scope = "activity%20heartrate%20profile"
        val authUrl = "https://www.fitbit.com/oauth2/authorize?response_type=code&client_id=$clientId&redirect_uri=$redirect&scope=$scope"
        // Launch browser
        val btn = findViewById<android.widget.Button>(R.id.btnOpenAuth)
        btn.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(authUrl)))
        }

        // Handle intent if app was opened via redirect
        intent.data?.let { uri ->
            val code = uri.getQueryParameter("code")
            Log.d("FitbitAuth", "Redirected with code: $code")
            if (code != null) {
                // DO NOT exchange code in-app. Send code to your backend to swap for tokens.
                infoTv.text = "Received code. Send this to your backend for token exchange."
            }
        }
    }
}
