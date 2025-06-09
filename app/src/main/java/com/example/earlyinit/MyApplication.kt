package com.example.earlyinit

import android.app.Application
import android.util.Log

/**  Process root. Verifies that native early-init ran
 *   *before* the Application constructor and logs outcome.
 */
class MyApplication : Application() {

    init {
        /* Fail-fast in debug; release builds could soft-report instead. */
        check(EarlyInitVerifier.wasExecuted()) {
            "Native early-init function did not run before Application()"
        }
        Log.i(TAG, "early native init verified")
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "MyApplication.onCreate()")
    }

    private companion object { const val TAG = "MyApplication" }
}
