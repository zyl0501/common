package com.ray.common.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public final class Rsa {
    private static final String ALGORITHM = "RSA";
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";


    private static PublicKey getPublicKeyFromX509(String var0, String var1) throws NoSuchAlgorithmException, Exception {
        byte[] var2 = Base64.decode(var1);
        X509EncodedKeySpec var3 = new X509EncodedKeySpec(var2);
        KeyFactory var4 = KeyFactory.getInstance(var0);
        return var4.generatePublic(var3);
    }

    public static String encrypt(String var0, String var1) {
        String var2 = null;
        ByteArrayOutputStream var3 = null;

        try {
            PublicKey var4 = getPublicKeyFromX509("RSA", var1);
            Cipher var5 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            var5.init(1, var4);
            byte[] var6 = var0.getBytes("UTF-8");
            int var7 = var5.getBlockSize();
            var3 = new ByteArrayOutputStream();

            for (int var8 = 0; var8 < var6.length; var8 += var7) {
                var3.write(var5.doFinal(var6, var8, var6.length - var8 < var7 ? var6.length - var8 : var7));
            }

            var2 = new String(Base64.encode(var3.toByteArray()));
        } catch (Exception var17) {
        } finally {
            if (var3 != null) {
                try {
                    var3.close();
                } catch (IOException var16) {
                }
            }
        }
        return var2;
    }

    public static byte[] encryptToByteArray(String var0, String var1) {
        byte[] var2 = null;
        ByteArrayOutputStream var3 = null;

        try {
            PublicKey var4 = getPublicKeyFromX509("RSA", var1);
            Cipher var5 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            var5.init(1, var4);
            byte[] var6 = var0.getBytes("UTF-8");
            int var7 = var5.getBlockSize();
            var3 = new ByteArrayOutputStream();

            for (int var8 = 0; var8 < var6.length; var8 += var7) {
                var3.write(var5.doFinal(var6, var8, var6.length - var8 < var7 ? var6.length - var8 : var7));
            }

            var2 = var3.toByteArray();
        } catch (Exception var17) {
        } finally {
            if (var3 != null) {
                try {
                    var3.close();
                } catch (IOException var16) {
                }
            }
        }
        return var2;
    }

    public static String decrypt(String var0, String var1) {
        ByteArrayOutputStream var2 = null;
        String var3 = null;

        try {
            PKCS8EncodedKeySpec var4 = new PKCS8EncodedKeySpec(Base64.decode(var1));
            KeyFactory var5 = KeyFactory.getInstance("RSA");
            PrivateKey var6 = var5.generatePrivate(var4);
            Cipher var7 = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            var7.init(2, var6);
            byte[] var8 = Base64.decode(var0);
            int var9 = var7.getBlockSize();
            var2 = new ByteArrayOutputStream();

            for (int var10 = 0; var10 < var8.length; var10 += var9) {
                var2.write(var7.doFinal(var8, var10, var8.length - var10 < var9 ? var8.length - var10 : var9));
            }

            var3 = new String(var2.toByteArray());
        } catch (Exception var19) {
        } finally {
            if (var2 != null) {
                try {
                    var2.close();
                } catch (IOException var18) {
                }
            }
        }

        return var3;
    }

    public static String sign(String var0, String var1) {
        String var2 = "utf-8";

        try {
            PKCS8EncodedKeySpec var3 = new PKCS8EncodedKeySpec(Base64.decode(var1));
            KeyFactory var4 = KeyFactory.getInstance("RSA");
            PrivateKey var5 = var4.generatePrivate(var3);
            Signature var6 = Signature.getInstance("SHA1WithRSA");
            var6.initSign(var5);
            var6.update(var0.getBytes(var2));
            byte[] var7 = var6.sign();
            return Base64.encode(var7);
        } catch (Exception var8) {
            return null;
        }
    }

    public static boolean doCheck(String var0, String var1, String var2) {
        try {
            KeyFactory var3 = KeyFactory.getInstance("RSA");
            byte[] var4 = Base64.decode(var2);
            PublicKey var5 = var3.generatePublic(new X509EncodedKeySpec(var4));
            Signature var6 = Signature.getInstance("SHA1WithRSA");
            var6.initVerify(var5);
            var6.update(var0.getBytes("utf-8"));
            boolean var7 = var6.verify(Base64.decode(var1));
            return var7;
        } catch (Exception var8) {
            return false;
        }
    }
}