package com.nic.mparivahan.core.zepto;

import java.io.ByteArrayOutputStream;

public abstract class e40 {
    public static final z12 a = new g40();

    public static byte[] a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((bArr.length / 4) * 3);
        try {
            a.c(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("unable to decode base64 data: " + e.getMessage(), e);
        }
    }

    public static byte[] b(byte[] bArr) {
        return c(bArr, 0, bArr.length);
    }

    public static byte[] c(byte[] bArr, int i, int i2) {
        z12 z12Var = a;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(z12Var.a(i2));
        try {
            z12Var.b(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new IllegalStateException("exception encoding base64 string: " + e.getMessage(), e);
        }
    }
}
