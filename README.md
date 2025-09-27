HealthSuite_Final - Android Studio Project (Starter)

Included features:
- Phone IMU access
- Shake (SOS) detection (ShakeDetector)
- Fall detection (FallDetector)
- Local persistence for events using Room (events: shake, fall)
- Dashboard with simple chart (MPAndroidChart) and counts of recent events
- Fitbit OAuth initiation (FitbitAuthActivity) with redirect intent handling stub (PUT client id in strings.xml and implement backend)
- Health Connect helper stub (you must implement actual permission flow per docs)

How to build:
1. Unzip the project and open in Android Studio (File -> Open -> select folder).
2. Gradle sync. You may need to enable Kotlin plugin and KAPT if prompted.
3. Connect a physical Android device (accelerometer required) and run.
4. For Fitbit: replace fitbit_client_id in app/src/main/res/values/strings.xml and register redirect URI in Fitbit dev console. Implement backend to exchange code for tokens (do not include client secret in app).
5. Health Connect: follow official docs to add required dependency version and permission flow.

Notes:
- Camera and activity recognition features removed as requested.
- Some SDK versions may need updates depending on your Android Studio; adjust Gradle plugin if required.
