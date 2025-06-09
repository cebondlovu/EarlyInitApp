/**  C++ file that owns the early-boot flag.
 *   The function annotated with constructor executes as soon as
 *   the shared object is linked – that’s *before* JVM returns from
 *   System.loadLibrary().
 */

#include <atomic>

static std::atomic<bool> g_executed{false};

__attribute__((constructor))
static void early_function() { g_executed.store(true, std::memory_order_release); }

/* Exported helper – used by JNI layer. */
extern "C" bool was_early_function_executed() {
    return g_executed.load(std::memory_order_acquire);
}
