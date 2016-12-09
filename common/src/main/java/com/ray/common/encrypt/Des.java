

package com.ray.common.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class Des {

    public static String encrypt(String data, String key) {
        return doFinal(1, data, key);
    }

    public static String decrypt(String data, String key) {
        return doFinal(2, data, key);
    }

    public static String doFinal(int flag, String data, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "DES");
            Cipher des = Cipher.getInstance("DES");
            des.init(flag, keySpec);
            byte[] bytes;
            if (flag == 2) {
                bytes = Base64.decode(data);
            } else {
                bytes = data.getBytes("UTF-8");
            }

            byte[] bytes1 = des.doFinal(bytes);
            if (flag == 2) {
                return new String(bytes1);
            } else {
                return Base64.encode(bytes1);
            }

        } catch (Exception e) {
            return null;
        }
    }
}
