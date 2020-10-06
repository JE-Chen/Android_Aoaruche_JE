/*
 * Copyright (c) 2018. JE-Chen
 */

package com.tw.je.aoaruche;


import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class JExAES {


    // 加密
    public static String encrypt128(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            return null;
        }
        // 判斷是否為 128bit 　Key
        if (sKey.length() != 16) {
            return null;
        }
        //用UTF-8編碼取得金鑰字串 轉 byte[]後的值
        byte[] raw = sKey.getBytes(Charset.defaultCharset());
        //創建金鑰 AES128
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

        Cipher cipher = Cipher.getInstance("AES");//算法//模式//補碼方式

        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);//加密模式//傳入金鑰
        //回傳加密完畢的內容
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(Charset.defaultCharset()));
        //使用Base64轉碼，並起到2次加密的效果。
        return Base64.encodeToString(encrypted, Base64.DEFAULT);
    }

    // 解密
    public static String decrypt128(String sSrc, String sKey) throws Exception {
        try {
            if (sKey == null) {
                return null;
            }

            if (sKey.length() != 16) {
                return null;
            }

            byte[] raw = sKey.getBytes(Charset.defaultCharset());

            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // "AES/ECB/PKCS5Padding"解密不能用於Android 4.3以上：
            //Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, skeySpec);

            byte[] encrypted1 = Base64.decode(sSrc.getBytes(Charset.defaultCharset()), Base64.DEFAULT);

            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, Charset.defaultCharset());
                return originalString;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.toString());
            return null;
        }
    }



    public String Passward(int Not_passward,long Mode){

        String[] GET_UPCASE ={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","_"};
        String[] GET_LCASE ={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","_"};
        String[] GET_NUMBER ={"1","2","3","4","5","6","7","8","9"};
        String  Over_String = "";

        if(Mode==1){

     Over_String+=GET_UPCASE[Not_passward];

        } else if(Mode==2){

            Over_String+=GET_LCASE[Not_passward];

        } else if(Mode==3){
            for (int k=0;k<16;k++) {
                int Rnd = (int) (Math.random()) * 26;
                int Rnd_number = (int) (Math.random()) * 9;
                int Rnd_Mode = (int) (Math.random()) * 2;
                if (Rnd_Mode == 0) {
                    Over_String += GET_UPCASE[Rnd];
                }else if (Rnd_Mode == 1) {
                    Over_String += GET_LCASE[Rnd];
                }else if (Rnd_Mode == 2) {
                    Over_String += GET_NUMBER[Rnd_number];
                }
            }
            return Over_String;
        }

        return Over_String;

    }


}
