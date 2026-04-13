package com.nic.mparivahan.core.common

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

fun generateAESKey(secret: String): ByteArray {
    require(secret.length >= 4) { "Minimum 4 characters required" }

    val len = secret.length

    // last 4 reversed
    val l1 = secret[len - 1]
    val l2 = secret[len - 2]
    val l3 = secret[len - 3]
    val l4 = secret[len - 4]

    // first 4 reversed
    val f1 = secret[3]
    val f2 = secret[2]
    val f3 = secret[1]
    val f4 = secret[0]

    val finalStr = buildString(16) {
        append(l1).append(l2).append(l3).append(l4)
        append(f1).append(f2).append(f3).append(f4)
        append("!~)#@*&^")
    }

    log("GenerateAESKey", State.PROCESS.value) { "KEY \uD83D\uDD10- $finalStr" }

    return finalStr.toByteArray() //🔐
}

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