package com.nic.mparivahan.core.common;

import static com.nic.mparivahan.core.common.ExtraFunKt.generateAESKey;
import static org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt.DECRYPT_MODE;
import static org.bouncycastle.pqc.jcajce.provider.util.CipherSpiExt.ENCRYPT_MODE;

import android.annotation.SuppressLint;

import com.nic.mparivahan.core.zepto.e40;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

public class EncryptionDecryption {

// region --- Custom json/key ---
    // 🔑 1. Generate AES Key (SHA-256 → 32 bytes)
    // 🔐 2. JSON → KEY (Encrypt)
    // 🔓 3. KEY → JSON (Decrypt)

// endregion

    public final String a(String r13, String stock) { // todo -> scrapping first // return map -> {0012MAC=4517933771!~)#@*&^} //Decryption (method a)
        //String r0 = "stock";
        //kotlin.jvm.internal.Intrinsics.checkNotNullParameter(stock, r0);
        Intrinsics.checkNotNullParameter(stock, "stock");

        try {
            //com.zepto.m70 r0_obj = new com.zepto.m70();
            //java.security.Security.addProvider(r0_obj);
            Security.addProvider(new BouncyCastleProvider());

            //String strValueOf = String.valueOf(new cf4(stock).a().get(APIController.a().getConst()));

/*
            //com.zepto.cf4 r1 = new com.zepto.cf4(stock);
            //java.util.HashMap r12_map = r1.a();
            java.util.HashMap r12_map = new cf4().a(stock);

            //com.nic.mparivahan.APIController r1_api = com.nic.mparivahan.APIController.a();
            //String r1_key = r1_api.getConst(); // 0012MAC
            String r1_key = "0012MAC";

            Object r12_val = r12_map.get(r1_key); // get value by key '0012MAC'
            String r12_str = String.valueOf(r12_val); // value convert to string
            Log.d("KEY_DEBUG", "Key length = "+r12_str);

            java.nio.charset.Charset r1_charset = java.nio.charset.Charset.forName("UTF8");
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1_charset, "forName(charsetName)");
*/

            //byte[] r12_bytes = r12_str.getBytes(r1_charset);
            byte[] r12_bytes = generateAESKey(stock);

            Intrinsics.checkNotNullExpressionValue(r12_bytes, "this as java.lang.String).getBytes(charset)");
            //LoggerFunctionKt.log("KEY_DEBUG" , State.PROCESS.getValue(), () ->  " PROCESS : KEY length - "+ new String(r12_bytes, Charsets.UTF_8).length() );


            SecretKeySpec r1_keySpec = new SecretKeySpec(r12_bytes, "AES");


            byte[] r13_bytes = null; // 32 size default of key

            // ===== CLEANED STRING TRIM LOGIC =====
            if (r13 != null) {
                int start = 0;
                int end = r13.length() - 1;
                boolean foundStart = false;

                while (start <= end) {
                    int index = foundStart ? end : start;
                    char ch = r13.charAt(index);

                    boolean isSpace = Intrinsics.compare(ch, 32) <= 0;

                    if (!foundStart) {
                        if (!isSpace) {
                            foundStart = true;
                        } else {
                            start++;
                        }
                    } else {
                        if (!isSpace) {
                            break;
                        } else {
                            end--;
                        }
                    }
                }

                String trimmed = r13.subSequence(start, end + 1).toString();

                Charset charset = Charset.forName("UTF8");
                Intrinsics.checkNotNullExpressionValue(charset, "forName(charsetName)");

                r13_bytes = trimmed.getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(r13_bytes, "this as java.lang.String).getBytes(charset)");
            }

            //byte[] r5 = androidx.navigation.e40.a(r13_bytes);
            byte[] r5 = e40.a(r13_bytes);

            synchronized ( Cipher.class) {
/*
                byte[] full = base64Decode(r13, 2);

                // first 16 bytes = IV
                //byte[] ivBytes = Arrays.copyOfRange(full, 0, 16);
                //GCMParameterSpec spec = new GCMParameterSpec(128, ivBytes);
                //Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                //cipher.init(Cipher.DECRYPT_MODE, r1_keySpec, spec);


                // remaining = actual encrypted data
                byte[] cipherBytes = Arrays.copyOfRange(full, 16, full.length);
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(Cipher.DECRYPT_MODE, r1_keySpec, new IvParameterSpec(cipherBytes));

                byte[] decrypted_ = cipher.doFinal(cipherBytes);
                String decrypted = new String(decrypted_);
*/

                @SuppressLint( "GetInstance" ) Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding"); // AES/CBC/PKCS5Padding
                cipher.init(DECRYPT_MODE, r1_keySpec);

                int outputSize = cipher.getOutputSize(r5.length);
                byte[] result = new byte[outputSize];

                int updateLen = cipher.update(r5, 0, r5.length, result, 0);
                //int finalLen = cipher.doFinal(result, updateLen);
                cipher.doFinal(result, updateLen);

                String decrypted = new String(result, Charsets.UTF_8);
                // ✅ only valid bytes
                //String decrypted = new String(result, 0, updateLen + finalLen, kotlin.text.Charsets.UTF_8);

                // ===== AGAIN SAME TRIM LOGIC =====
                int start = 0;
                int end = decrypted.length() - 1;
                boolean foundStart = false;

                while (start <= end) {
                    int index = foundStart ? end : start;
                    char ch = decrypted.charAt(index);

                    boolean isSpace = Intrinsics.compare(ch, 32) <= 0;

                    if (!foundStart) {
                        if (!isSpace) {
                            foundStart = true;
                        } else {
                            start++;
                        }
                    } else {
                        if (!isSpace) {
                            break;
                        } else {
                            end--;
                        }
                    }
                }

                return decrypted.subSequence(start, end + 1).toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final String b(String strToEncrypt, String stock) {
        String str;
        Intrinsics.checkNotNullParameter(strToEncrypt, "strToEncrypt");
        Intrinsics.checkNotNullParameter(stock, "stock");

        Security.addProvider(new BouncyCastleProvider());
        try {
/*
            //String reversed = new StringBuilder(stock).reverse().toString();
            //String strValueOf = reversed + "!~)#@*&^"; U1ZnMGNFa3dRWEZLYkRROQ==
            String strValueOf = String.valueOf(new cf4().a(stock).get("0012MAC"));

            Charset charsetForName = Charset.forName("UTF8");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");

            byte[] bytes = strValueOf.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
*/

            byte[] keyBytes = generateAESKey(stock);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
//            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "AES");

            Charset charsetForName2 = Charset.forName("UTF8");
            Intrinsics.checkNotNullExpressionValue(charsetForName2, "forName(charsetName)");

            byte[] bytes2 = strToEncrypt.getBytes(charsetForName2);
            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");

            synchronized (Cipher.class) {
                @SuppressLint( "GetInstance" ) Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");

                cipher.init(ENCRYPT_MODE, secretKeySpec);

                byte[] bArr = new byte[cipher.getOutputSize(bytes2.length)];
                cipher.doFinal(bArr, cipher.update(bytes2, 0, bytes2.length, bArr, 0));

                byte[] bArrB = e40.b(bArr);
                Intrinsics.checkNotNullExpressionValue(bArrB, "encode(...)");

                Charset charset = Charsets.UTF_8;
                byte[] bytes3 = new String(bArrB, charset).getBytes(charset);
                Intrinsics.checkNotNullExpressionValue(bytes3, "this as java.lang.String).getBytes(charset)");

                byte[] bArrB2 = e40.b(bytes3);
                Intrinsics.checkNotNullExpressionValue(bArrB2, "encode(...)");

                str = new String(bArrB2, charset);
            }
            return str;
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//                return "Error";
        } catch (InvalidKeyException e2) {
            e2.printStackTrace();
            return "Error";
        } catch (NoSuchAlgorithmException e3) {
            e3.printStackTrace();
            return "Error";
        } catch (BadPaddingException e4) {
            e4.printStackTrace();
            return "Error";
        } catch (IllegalBlockSizeException e5) {
            e5.printStackTrace();
            return "Error";
        } catch (NoSuchPaddingException e6) {
            e6.printStackTrace();
            return "Error";
        } catch (ShortBufferException e7) {
            e7.printStackTrace();
            return "Error";
        } catch (Exception e8) {
            e8.printStackTrace();
            return "Error";
        }
    }
}
