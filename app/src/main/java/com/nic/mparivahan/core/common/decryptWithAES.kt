package com.nic.mparivahan.core.common



import android.annotation.SuppressLint
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

// 🔑 1. Generate AES Key (SHA-256 → 32 bytes)
// 🔐 2. JSON → KEY (Encrypt)
// 🔓 3. KEY → JSON (Decrypt)
@SuppressLint("GetInstance")
fun String.decryptWithAES(key: String): String? {
    require(key.isNotEmpty()) { "key cannot be empty" }

    return try {
        Security.addProvider(BouncyCastleProvider())

        val keyBytes = key.generateAESKeyBytes()
        val keySpec = SecretKeySpec(keyBytes, "AES")

        val trimmedInput = this.trim()
        if (trimmedInput.isEmpty()) return null

        val inputBytes = trimmedInput.toByteArray(Charsets.UTF_8)

        // decode (same as e40.a)
        //val decodedBytes = e40.a(inputBytes)
        val decodedBytes = java.util.Base64.getDecoder().decode(inputBytes)

        synchronized(Cipher::class.java) {

            val cipher = Cipher.getInstance("AES/ECB/PKCS7Padding")
            cipher.init(Cipher.DECRYPT_MODE, keySpec)

            val result = ByteArray(cipher.getOutputSize(decodedBytes.size))
            val updateLen = cipher.update(decodedBytes, 0, decodedBytes.size, result, 0)
            val finalLen = cipher.doFinal(result, updateLen)

            val decrypted = String(result, 0, updateLen + finalLen, Charsets.UTF_8)

            decrypted.trim()
        }

    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
