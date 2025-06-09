# EarlyInitApp &nbsp;–&nbsp; run native code **before** `Application`

A production-grade reference that guarantees a custom native function executes
**first** in your Android process while remaining:

* **Deterministic** – identical ordering on every API 24-35 device
* **Direct-Boot / FBE safe** – works even when the phone is still locked
* **Scalable** – four ABIs, AGP 8.7, Compose 1.8.2, Java 17  

---

## How it works

| Stage | Component | What happens |
|-------|-----------|--------------|
| ① | **`EarlyInitComponentFactory`** | Framework instantiates it <br>*(manifest `android:appComponentFactory`)* |
| ② | Kotlin `<clinit>` | `System.loadLibrary("earlyinit")` |
| ③ | **Dynamic linker** | Maps `libearlyinit.so`, runs `early_function()` <br>`__attribute__((constructor))` |
| ④ | **`MyApplication`** | Constructor calls `EarlyInitVerifier.wasExecuted()` (JNI) – aborts if flag == false |
| ⑤ | Normal app | `onCreate()` → Compose UI |

No reflection, no hidden APIs, no races.

---

## Build & run

```bash
git clone https://github.com/<your-org>/EarlyInitApp.git
cd EarlyInitApp

# one-time wrapper download → Gradle 8.9
./gradlew clean :app:assembleDebug

adb install -r app/build/outputs/apk/debug/app-debug.apk
adb logcat -s MyApplication