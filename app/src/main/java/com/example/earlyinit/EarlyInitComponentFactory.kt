package com.example.earlyinit

import android.app.AppComponentFactory
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi

/**  Factory instantiated by Android **before** Application.
 *   Loads the native library in a static init block so the ELF
 *   constructor (early_function()) executes immediately.
 */
@RequiresApi(Build.VERSION_CODES.P)
class EarlyInitComponentFactory : AppComponentFactory() {

    /* Load libearlyinit.so – idempotent even if called twice.
   The moment this line runs the linker triggers early_function(). */
    init { System.loadLibrary("earlyinit") }

    /** Required override – delegate to super. Both params are @NonNull in SDK-35. */
    override fun instantiateApplication(
        cl: ClassLoader,
        className: String): Application = super.instantiateApplication(cl, className)
}
