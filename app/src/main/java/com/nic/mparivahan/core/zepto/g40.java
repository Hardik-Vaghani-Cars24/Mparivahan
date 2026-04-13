package com.nic.mparivahan.core.zepto;

import java.io.IOException;
import java.io.OutputStream;

import kotlin.UByte;
import kotlin.io.encoding.Base64;

public class g40 implements z12 {
    public final byte[] a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    public byte b = Base64.padSymbol;
    public final byte[] c = new byte[128];

    public g40() {
        g();
    }

    @Override // androidx.navigation.z12
    public int a(int i) {
        return ((i + 2) / 3) * 4;
    }

    @Override // androidx.navigation.z12
    public int b(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        if (i2 < 0) {
            return 0;
        }
        byte[] bArr2 = new byte[72];
        int i3 = i2;
        while (i3 > 0) {
            int iMin = Math.min(54, i3);
            outputStream.write(bArr2, 0, e(bArr, i, iMin, bArr2, 0));
            i += iMin;
            i3 -= iMin;
        }
        return ((i2 + 2) / 3) * 4;
    }

    @Override // androidx.navigation.z12
    public int c(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        byte[] bArr2 = new byte[54];
        int i3 = i + i2;
        while (i3 > i && f((char) bArr[i3 - 1])) {
            i3--;
        }
        if (i3 == 0) {
            return 0;
        }
        int i4 = i3;
        int i5 = 0;
        while (i4 > i && i5 != 4) {
            if (!f((char) bArr[i4 - 1])) {
                i5++;
            }
            i4--;
        }
        int iH = h(bArr, i, i4);
        int i6 = 0;
        int i7 = 0;
        while (iH < i4) {
            int i8 = iH + 1;
            byte b = this.c[bArr[iH]];
            int iH2 = h(bArr, i8, i4);
            int i9 = iH2 + 1;
            byte b2 = this.c[bArr[iH2]];
            int iH3 = h(bArr, i9, i4);
            int i10 = iH3 + 1;
            byte b3 = this.c[bArr[iH3]];
            int iH4 = h(bArr, i10, i4);
            int i11 = iH4 + 1;
            byte b4 = this.c[bArr[iH4]];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            bArr2[i6] = (byte) ((b << 2) | (b2 >> 4));
            int i12 = i6 + 2;
            bArr2[i6 + 1] = (byte) ((b2 << 4) | (b3 >> 2));
            i6 += 3;
            bArr2[i12] = (byte) ((b3 << 6) | b4);
            if (i6 == 54) {
                outputStream.write(bArr2);
                i6 = 0;
            }
            i7 += 3;
            iH = h(bArr, i11, i4);
        }
        if (i6 > 0) {
            outputStream.write(bArr2, 0, i6);
        }
        int iH5 = h(bArr, iH, i3);
        int iH6 = h(bArr, iH5 + 1, i3);
        int iH7 = h(bArr, iH6 + 1, i3);
        return i7 + d(outputStream, (char) bArr[iH5], (char) bArr[iH6], (char) bArr[iH7], (char) bArr[h(bArr, iH7 + 1, i3)]);
    }

    public final int d(OutputStream outputStream, char c, char c2, char c3, char c4) throws IOException {
        //char c5 = this.b;
        char c5 = (char) this.b;
        if (c3 == c5) {
            if (c4 != c5) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            byte[] bArr = this.c;
            byte b = bArr[c];
            byte b2 = bArr[c2];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            return 1;
        }
        if (c4 == c5) {
            byte[] bArr2 = this.c;
            byte b3 = bArr2[c];
            byte b4 = bArr2[c2];
            byte b5 = bArr2[c3];
            if ((b3 | b4 | b5) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            outputStream.write((b3 << 2) | (b4 >> 4));
            outputStream.write((b4 << 4) | (b5 >> 2));
            return 2;
        }
        byte[] bArr3 = this.c;
        byte b6 = bArr3[c];
        byte b7 = bArr3[c2];
        byte b8 = bArr3[c3];
        byte b9 = bArr3[c4];
        if ((b6 | b7 | b8 | b9) < 0) {
            throw new IOException("invalid characters encountered at end of base64 data");
        }
        outputStream.write((b6 << 2) | (b7 >> 4));
        outputStream.write((b7 << 4) | (b8 >> 2));
        outputStream.write((b8 << 6) | b9);
        return 3;
    }

    public int e(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = (i + i2) - 2;
        int i5 = i;
        int i6 = i3;
        while (i5 < i4) {
            byte b = bArr[i5];
            int i7 = i5 + 2;
            int i8 = bArr[i5 + 1] & UByte.MAX_VALUE;
            i5 += 3;
            byte b2 = bArr[i7];
            int i9 = b2 & UByte.MAX_VALUE;
            byte[] bArr3 = this.a;
            bArr2[i6] = bArr3[(b >>> 2) & 63];
            bArr2[i6 + 1] = bArr3[((b << 4) | (i8 >>> 4)) & 63];
            int i10 = i6 + 3;
            bArr2[i6 + 2] = bArr3[((i8 << 2) | (i9 >>> 6)) & 63];
            i6 += 4;
            bArr2[i10] = bArr3[b2 & 63];
        }
        int i11 = i2 - (i5 - i);
        if (i11 == 1) {
            int i12 = bArr[i5] & UByte.MAX_VALUE;
            byte[] bArr4 = this.a;
            bArr2[i6] = bArr4[(i12 >>> 2) & 63];
            bArr2[i6 + 1] = bArr4[(i12 << 4) & 63];
            int i13 = i6 + 3;
            byte b3 = this.b;
            bArr2[i6 + 2] = b3;
            i6 += 4;
            bArr2[i13] = b3;
        } else if (i11 == 2) {
            int i14 = i5 + 1;
            int i15 = bArr[i5] & UByte.MAX_VALUE;
            int i16 = bArr[i14] & UByte.MAX_VALUE;
            byte[] bArr5 = this.a;
            bArr2[i6] = bArr5[(i15 >>> 2) & 63];
            bArr2[i6 + 1] = bArr5[((i15 << 4) | (i16 >>> 4)) & 63];
            int i17 = i6 + 3;
            bArr2[i6 + 2] = bArr5[(i16 << 2) & 63];
            i6 += 4;
            bArr2[i17] = this.b;
        }
        return i6 - i3;
    }

    public final boolean f(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public void g() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.c;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.a;
            if (i >= bArr2.length) {
                return;
            }
            this.c[bArr2[i]] = (byte) i;
            i++;
        }
    }

    public final int h(byte[] bArr, int i, int i2) {
        while (i < i2 && f((char) bArr[i])) {
            i++;
        }
        return i;
    }
}
