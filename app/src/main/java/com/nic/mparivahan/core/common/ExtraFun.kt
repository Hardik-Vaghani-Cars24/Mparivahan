package com.nic.mparivahan.core.common

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nic.mparivahan.core.util.BASE_TAG
fun String.generateAESKeyBytes(): ByteArray {
    return this.generateAESKey().toByteArray()
}

fun String.generateAESKey(): String {
    require(this.length >= 4) { "Minimum 4 characters required" }

    val len = this.length

    // last 4 reversed
    val l1 = this[len - 1]
    val l2 = this[len - 2]
    val l3 = this[len - 3]
    val l4 = this[len - 4]

    // first 4 reversed
    val f1 = this[3]
    val f2 = this[2]
    val f3 = this[1]
    val f4 = this[0]

    val finalStr = buildString(16) {
        append(l1).append(l2).append(l3).append(l4)
        append(f1).append(f2).append(f3).append(f4)
        append("!~)#@*&^")
    }

    log("${BASE_TAG}GenerateAESKey", State.PROCESS.value) { "KEY 🔐- $finalStr" }

    return finalStr
}

fun String.decode64(): String {
    val decodeData = android.util.Base64.decode(this, 0)
    return String(decodeData, Charsets.UTF_8)
}

fun String?.isNullLike(): Boolean =
    this == null || isBlank() ||
            equals("null", ignoreCase = false) ||
            equals("NA", ignoreCase = true)

fun getEndpoint(url : String , flag : Int = 1) : String {
    return when (flag) {
        1 -> url.trimEnd('/').substringAfterLast("/")
        else -> url.substringAfter("://").substringAfter("/")
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun base64Decode(data: String , flag: Int = 1): ByteArray {
    return when (flag) {
        // 🥇 1 → Java Base64 (JVM standard, oldest stable)
        1 -> java.util.Base64.getDecoder().decode(data)

        // 🥈 2 → Android Base64
        2 -> android.util.Base64.decode(data, android.util.Base64.DEFAULT)

        // 🥉 3 → Kotlin Base64 (newest)
        3 -> kotlin.io.encoding.Base64.decode(data)

        else -> throw IllegalArgumentException("Invalid flag")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun base64Encode(data: ByteArray , flag: Int = 1): String {
    return when (flag) {

        // 🥇 1 → Java Base64 (JVM standard)
        1 -> java.util.Base64.getEncoder().encodeToString(data)

        // 🥈 2 → Android Base64
        2 -> android.util.Base64.encodeToString(data, android.util.Base64.NO_WRAP)

        // 🥉 3 → Kotlin Base64
        3 -> kotlin.io.encoding.Base64.encode(data)

        else -> throw IllegalArgumentException("Invalid flag")
    }
}

fun <T> String.toModel(): T {
    val type = object : TypeToken<T>() {}.type
    return Gson().fromJson(this, type)
}

fun <T> getType(clazz: Class<T>): Class<T> {
    return clazz
}