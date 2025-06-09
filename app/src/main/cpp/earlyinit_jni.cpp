/**  Minimal JNI glue – keeps surface area tiny and auditable. */
#include <jni.h>

extern "C" bool was_early_function_executed();

/* Kotlin -> native bridge */
extern "C"
JNIEXPORT jboolean JNICALL
Java_com_example_earlyinit_EarlyInitVerifier_wasExecuted(JNIEnv*, jclass) {
    return was_early_function_executed() ? JNI_TRUE : JNI_FALSE;
}

/* Future-proof hook; currently just declares version. */
extern "C"
JNIEXPORT jint JNICALL
JNI_OnLoad(JavaVM*, void*) { return JNI_VERSION_1_6; }
