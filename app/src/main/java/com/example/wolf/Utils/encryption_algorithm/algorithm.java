package com.example.wolf.Utils.encryption_algorithm;

public class algorithm {

   public static byte[] encryptDecode(byte[] bt){


        if (bt != null) {
            for (int i = 0; i < bt.length; i++) {
                bt[i] = (byte) (bt[i] + 128);
                bt[i] = (byte) ~bt[i];
                bt[i] ^= 0x72;
                bt[i] -= (char) 128;
            }
        }
        return bt;

    }

}
