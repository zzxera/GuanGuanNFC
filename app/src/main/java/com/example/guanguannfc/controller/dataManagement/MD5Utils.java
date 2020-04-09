package com.example.guanguannfc.controller.dataManagement;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
    public static String md5(String text){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : result){
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1){
                    stringBuffer.append("0"+hex);
                }else {
                    stringBuffer.append(hex);
                }
            }
            return stringBuffer.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return "";
        }
    }
}
