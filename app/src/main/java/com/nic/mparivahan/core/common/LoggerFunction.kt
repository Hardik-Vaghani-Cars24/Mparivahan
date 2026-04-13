package com.nic.mparivahan.core.common

import android.util.Log

enum class State(val value: Pair<String, String>) {

    INIT       (Pair("🟣➤", "👉 ")),
    CALL       (Pair("🔵➤", "👉🏻 ")),
    ENTERED    (Pair("🟢➤", "👉🏼 ")),
    PROCESS    (Pair("🔶➤", "👉🏽 ")),
    WAITING    (Pair("🟡➤", "👉🏾 ")),
    HOLD       (Pair("🟠➤", "👉🏿 ")),
    SUCCESS    (Pair("🟢➤", "👉 ")),
    ERROR      (Pair("🔴➤", "👉🏻 ")),
    EXIT       (Pair("⚫➤", "👉🏼 ")),
    END        (Pair("⚪➤", "👉🏽 ")),

    // extra useful
    CONFUSE    (Pair("🟤➤", "👉🏾 ")),
    DATA       (Pair("🔷➤", "👉🏿 ")),
    RESPONSE   (Pair("🟩➤", "👉 ")),
    RETRY      (Pair("🟨➤", "👉🏻 ")),
    NORMAL     (Pair("⬜➤", "👉🏼 ")),
    UNDEFINED  (Pair("❓➤", "👉🏽 ")),

}


inline fun log(
    tag: String = "LOG" ,
    state: Pair<String, String> = State.INIT.value ,
    crossinline message: () -> String
) {
    Log.d("${state.first} [$tag] ", "${state.second}${message()}")   // ✅ visible in Logcat
}


//in java call   -> LoggerFunctionKt.log("getJsonString",GREEN,() -> "✅ SUCCESS [$type] stock=$stock → $result");
//in Kotlin call -> log("ENCRYPT") { "Encrypted: $encrypted" }
//in Kotlin call -> log("DECRYPT", INIT) { "Decrypted: $decrypted" }
//in Kotlin call -> log("ERROR", INIT) { "Something failed" }