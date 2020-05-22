package com.example.guanguannfc.controller.nfcManagement;

import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Parcelable;
import android.widget.Toast;

import com.example.guanguannfc.model.Dao.DaoActivity;
import com.example.guanguannfc.model.Dao.DaoBox;
import com.example.guanguannfc.model.Dao.DaoBoxContent;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Locale;

public class NFCManage extends BaseNfcActivity{
    private String username;
    private Context context;

    DaoActivity daoActivity;
    DaoBox daoBox;
    DaoBoxContent mDaoBoxContent;

    public NFCManage(String username, Context context){
        this.username = username;
        this.context = context;
        daoActivity = new DaoActivity(context);
        daoBox = new DaoBox(context);
        mDaoBoxContent = new DaoBoxContent(context);
    }

    //判断NFC存在与否：是空的，还是活动的，还是盒子的。
    public static String isNFCExist(String mTagText){
        String string = null;
        if (mTagText.length()>=3){
            if (mTagText == null){
                string = null;
            }else {
                if (mTagText.substring(0,3) .equals( "Act")){
                    string = "Act";
                }else if (mTagText.substring(0,3).equals("Box") ){
                    string = "Box";
                }
            }
            return string;
        }

        return string;


    }

    //根据username和NFC的编码返回活动名称
    public String[] nfcForActivity(String mTagText){
        return daoActivity.queryActivityByNFC(mTagText);
    }
    //根据username和NFC的编码返回盒子名称
    public String nfcForBox(String mTagText){
        return daoBox.queryBoxByNFC(mTagText);
    }

    //对没有进行使用过的NFC进行号码编写
    public boolean setNFCNumberForAct(String bigActivity, String smallActivity, Tag detectedTag){
        try {
            String NFCNumber = "Act" + username + System.currentTimeMillis();
            daoActivity.insert(username, NFCNumber, bigActivity, smallActivity);
            NdefMessage ndefMessage = new NdefMessage(
                    new NdefRecord[]{createTextRecord(NFCNumber)});
            writeTag(ndefMessage, detectedTag);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean setNFCNumberForBox(String boxName, String location, Tag detectedTag){
        try {
            String NFCNumber = "Box" + username + System.currentTimeMillis();
            daoBox.insert(username, NFCNumber, boxName, location);
            NdefMessage ndefMessage = new NdefMessage(
                    new NdefRecord[]{createTextRecord(NFCNumber)});
            writeTag(ndefMessage, detectedTag);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public boolean setNFCNll(Tag detectedTag){
        try {
            String NFCNumber = "";
            NdefMessage ndefMessage = new NdefMessage(
                    new NdefRecord[]{createTextRecord(NFCNumber)});
            writeTag(ndefMessage, detectedTag);
        }catch (Exception e){
            return false;
        }
        return true;
    }

//    public Boolean getResult(Intent intent,String mText) {
//        //获取Tag对象
//        Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//        NdefMessage ndefMessage = new NdefMessage(
//                new NdefRecord[]{createTextRecord(mText)});
//        boolean result = writeTag(ndefMessage, detectedTag);
//        return result;
//    }

    public static NdefRecord createTextRecord(String text) {
        byte[] langBytes = Locale.CHINA.getLanguage().getBytes(Charset.forName("US-ASCII"));
        Charset utfEncoding = Charset.forName("UTF-8");
        //将文本转换为UTF-8格式
        byte[] textBytes = text.getBytes(utfEncoding);
        //设置状态字节编码最高位数为0
        int utfBit = 0;
        //定义状态字节
        char status = (char) (utfBit + langBytes.length);
        byte[] data = new byte[1 + langBytes.length + textBytes.length];
        //设置第一个状态字节，先将状态码转换成字节
        data[0] = (byte) status;
        //设置语言编码，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1到langBytes.length的位置
        System.arraycopy(langBytes, 0, data, 1, langBytes.length);
        //设置文本字节，使用数组拷贝方法，从0开始拷贝到data中，拷贝到data的1 + langBytes.length
        //到textBytes.length的位置
        System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length);
        //通过字节传入NdefRecord对象
        //NdefRecord.RTD_TEXT：传入类型 读写
        NdefRecord ndefRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                NdefRecord.RTD_TEXT, new byte[0], data);
        return ndefRecord;
    }

    public static boolean writeTag(NdefMessage ndefMessage, Tag tag) {
        try {
            Ndef ndef = Ndef.get(tag);
            ndef.connect();
            ndef.writeNdefMessage(ndefMessage);
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public static String readNfcTag(Intent intent) {
        if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(
                    NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage msgs[] = null;
            int contentSize = 0;
            if (rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                    contentSize += msgs[i].toByteArray().length;
                }
            }
            try {
                if (msgs != null) {
                    NdefRecord record = msgs[0].getRecords()[0];
                    String textRecord = parseTextRecord(record);
                    return textRecord;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public static String parseTextRecord(NdefRecord ndefRecord) {
        /**
         * 判断数据是否为NDEF格式
         */
        //判断TNF
        if (ndefRecord.getTnf() != NdefRecord.TNF_WELL_KNOWN) {
            return null;
        }
        //判断可变的长度的类型
        if (!Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
            return null;
        }
        try {
            //获得字节数组，然后进行分析
            byte[] payload = ndefRecord.getPayload();
            //下面开始NDEF文本数据第一个字节，状态字节
            //判断文本是基于UTF-8还是UTF-16的，取第一个字节"位与"上16进制的80，16进制的80也就是最高位是1，
            //其他位都是0，所以进行"位与"运算后就会保留最高位
            String textEncoding = ((payload[0] & 0x80) == 0) ? "UTF-8" : "UTF-16";
            //3f最高两位是0，第六位是1，所以进行"位与"运算后获得第六位
            int languageCodeLength = payload[0] & 0x3f;
            //下面开始NDEF文本数据第二个字节，语言编码
            //获得语言编码
            String languageCode = new String(payload, 1, languageCodeLength, "US-ASCII");
            //下面开始NDEF文本数据后面的字节，解析出文本
            String textRecord = new String(payload, languageCodeLength + 1,
                    payload.length - languageCodeLength - 1, textEncoding);
            return textRecord;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }


}
