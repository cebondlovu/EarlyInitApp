package com.example.earlyinit

/**  Thin JNI façade so Kotlin/Java code can ask
 *   “did the native constructor run?” without exposing JNI junk.
 */
object EarlyInitVerifier {
    /* Safe double-load; if EarlyInitComponentFactory already loaded the
   library, the linker just bumps a ref-count. */
    init { System.loadLibrary("earlyinit") }

    /** Returns true if early_function() flipped the flag. */
    @JvmStatic external fun wasExecuted(): Boolean
}

