package com.digo.utils;

import android.util.Base64;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESMall {
	
	private static final String KEY="";
	private static final String IV="YM2HY4IH-34RL-13";
//	static   Logger   logger   =   Logger.getLogger(AESMall.class);
	private static final String PARTNER_KEY="0CC35A54BFE8F02CBCFE1E9DE3C7E4B4";
	
	  // 加密   "utf-8"

	public static void main(String[] args) throws  Exception {
		  Map signMap=new HashMap();
		  signMap.put("beginDate","2015-01-01 11:22:33");
		  signMap.put("callercode", "callercode");
		  signMap.put("cardNo", "K2015050442");
		  signMap.put("endDate", "2115-01-01 11:22:33");
		  signMap.put("method", "3005");
		  signMap.put("opt","U" );
		  signMap.put("timestamp","2015-05-08 16:34:33");
		  signMap.put("ttid","3");
		  signMap.put("userName","mobtest");
		  signMap.put("v","1");
		  String sipSign=AESMall.signParams(signMap);
//		  System.out.println(sipSign);
	
	}

	
	
	public static boolean checkApiSign(Map<String, Object> params) throws Exception{
		boolean flag = false;
		String clientSign = (String) params.get("sign");
		if (clientSign!=""||clientSign!=null) {
			params.remove("sign");
			String serverSign = AESMall.signParams(params);
			if (serverSign.equals(clientSign)) {
				flag = true;
			} 
		}
		return flag;
	}
	
	public static LinkedList<DSNameValuePair> sortParam(Map<String, Object> params){
		LinkedList<DSNameValuePair> pairs = new LinkedList<DSNameValuePair>();
		ArrayList<String> temp = new ArrayList<String>();
		for (String key : params.keySet()) {
			temp.add(key);
		}
		temp.trimToSize();
		Collections.sort(temp);
		for (String string : temp) {
			pairs.add(new DSNameValuePair(string,  params.get(string)));
		}
		return pairs;
	}
	
	public static String signParams(Map<String, Object> params) throws UnsupportedEncodingException {
		
		LinkedList<DSNameValuePair> paramList = sortParam(params);
		
		StringBuilder sb = new StringBuilder();		
		for (DSNameValuePair pair : paramList) {
			sb.append(pair.getKey()).append("=").append(pair.getValue()).append("&");
		}
		sb.append("key=").append(PARTNER_KEY); 
		
		String sign = getMessageDigest(sb.toString().getBytes("utf-8")).toUpperCase(Locale.US);
		
		return sign;
	}
	
	
	public final static String getMessageDigest(byte[] buffer) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(buffer);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	

    public static String Encrypt(String sSrc,String encode) throws Exception {  
        
        byte[] raw = KEY.getBytes();  
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"  
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度  
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);  
        byte[] encrypted=null;
        if("".equals(encode))
        {
         encrypted = cipher.doFinal(sSrc.getBytes());  
        }
        else
        {
        	 encrypted = cipher.doFinal(sSrc.getBytes(encode));  
        }
  
//        return new BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
		return new String( Base64.encode( encrypted , Base64.DEFAULT ) ) ;
    }
    
    // 解密  
    public static String Decrypt(String sSrc) throws Exception {  
        try {  
           
        	byte[] raw = KEY.getBytes("ASCII");  
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");  
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
            IvParameterSpec iv = new IvParameterSpec(IV.getBytes());  
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);  
//            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
            byte[] encrypted1 = Base64.decode( sSrc , Base64.DEFAULT ) ;//先用base64解密

            try {  
                byte[] original = cipher.doFinal(encrypted1);  
                String originalString = new String(original,"utf-8");
                return originalString;  
            } catch (Exception e) {  
//                logger.error("Decrypt=======================erro=============="+e.toString());
				e.printStackTrace() ;
                return null;
            }  
        } catch (Exception ex) {  
//            logger.error("Decrypt=======================erro=============="+ex.toString());
			ex.printStackTrace() ;
            return "";  
        }  
    }  
}

