package com.nic.mparivahan.data.network.interceptor

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.nic.mparivahan.core.common.EncryptionDecryption
import com.nic.mparivahan.core.common.State
import com.nic.mparivahan.core.common.generateAESKey
import com.nic.mparivahan.core.common.log
import com.nic.mparivahan.core.util.BASE_TAG
import com.nic.mparivahan.data.datasource.remote.dto.SecurityDto
import com.nic.mparivahan.domain.model.SecurityModel
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okio.Buffer
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.nio.charset.StandardCharsets
import java.security.Security
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class BodyEncryptionInterceptor @Inject constructor(context: Context) : Interceptor {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun intercept(chain: Interceptor.Chain): Response {
        val gson = Gson()
        val request = chain.request()
        val body = request.body
        val header = request.header("timestamp")

        val buffer = Buffer()
        body?.writeTo(buffer)
        val readUtf8 = buffer.readUtf8()
        val parse = "application/json;".toMediaTypeOrNull()
        val json = gson.toJson(SecurityModel(encrypt(readUtf8 , header.toString())))

        log(BASE_TAG , com.nic.mparivahan.core.common.State.PROCESS.value) { "encrypt json requestBody: $json" }

        val create = json.toRequestBody(parse)
        return chain.proceed(
            request.newBuilder()
                .header("Content-Type", "application/json")
                .header("Content-Length", create.contentLength().toString())
                .method(request.method, create)
                .build()
        )
    }
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("GetInstance")
    private fun encrypt(strToEncrypt: String , timeStamp: String): String {
        Security.addProvider(BouncyCastleProvider())
        return try {
            val charset = StandardCharsets.UTF_8
            val key = timeStamp.generateAESKey()

            log(BASE_TAG , State.PROCESS.value) { "Encrypt Key: $key" }

            val bytes = key.toByteArray(charset)
            val secretKeySpec = SecretKeySpec(bytes, "AES")
            val bytes2 = strToEncrypt.toByteArray(charset)
            synchronized(Cipher::class.java) {
                val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec)

                val bArr = ByteArray(cipher.getOutputSize(bytes2.size))
                cipher.doFinal(bArr, cipher.update(bytes2, 0, bytes2.size, bArr, 0))

                val encode = java.util.Base64.getEncoder().encode(bArr)
                val bytes3 = String(encode, Charsets.UTF_8).toByteArray(Charsets.UTF_8)

                val encode2 = java.util.Base64.getEncoder().encode(bytes3)
                String(encode2, Charsets.UTF_8)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error"
        }
    }
}