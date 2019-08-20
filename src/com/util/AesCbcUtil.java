package com.util;
 
import java.util.Locale;
 
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
 
/**
 * AES���ܽ����ַ���������
 * ������AES�߼����ܱ�׼���ǶԳ���Կ�����������е��㷨֮һ��
 *       ����ģʽ������ECB��CBC��CTR��OFB��CFB��
 * ʹ�÷�Χ���ù������֧��CBCģʽ�µģ�
 *              ��䣺PKCS7PADDING
 *              ���ݿ飺128λ
 *              ���루key����32�ֽڳ��ȣ����磺12345678901234567890123456789012��
 *              ƫ������iv����16�ֽڳ��ȣ����磺1234567890123456��
 *              �����hex
 *              �ַ�����UTF-8
 * ʹ�÷�ʽ��String encrypt = AESCBCUtil.encrypt("wy");
 *           String decrypt = AESCBCUtil.decrypt(encrypt);
 * ��֤��ʽ��http://tool.chacuo.net/cryptaes������AES���ܽ��ܣ�
 */
public class AesCbcUtil {
 
    //����
    private static final String key = "12345678901234567890123456789012";
    //ivƫ����
    private static final String iv = "1234567890123456";
 
    /**
     * ���ܣ����ַ������м��ܣ�������ʮ�������ַ���(hex)
     *
     * @param encryptStr ��Ҫ���ܵ��ַ���
     * @return ���ܺ��ʮ�������ַ���(hex)
     */
    public static String encrypt(String encryptStr) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
 
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
 
            byte[] encrypted = cipher.doFinal(encryptStr.getBytes());
 
            return byte2HexStr(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return null;
    }
 
    /**
     * ���ܣ��Լ��ܺ��ʮ�������ַ���(hex)���н��ܣ��������ַ���
     *
     * @param encryptedStr ��Ҫ���ܵģ����ܺ��ʮ�������ַ���
     * @return ���ܺ���ַ���
     */
    public static String decrypt(String encryptedStr) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
 
 
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
 
            byte[] bytes = hexStr2Bytes(encryptedStr);
            byte[] original = cipher.doFinal(bytes);
 
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
 
        return null;
    }
 
    /**
     * ʮ�������ַ���ת��Ϊbyte[]
     *
     * @param hexStr ��Ҫת��Ϊbyte[]���ַ���
     * @return ת�����byte[]
     */
    public static byte[] hexStr2Bytes(String hexStr) {
        /*������ֵ���й淶������*/
        hexStr = hexStr.trim().replace(" ", "").toUpperCase(Locale.US);
        //����ֵ��ʼ��
        int m = 0, n = 0;
        int iLen = hexStr.length() / 2; //���㳤��
        byte[] ret = new byte[iLen]; //����洢�ռ�
 
        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + hexStr.substring(i * 2, m) + hexStr.substring(m, n)) & 0xFF);
        }
        return ret;
    }
 
    /**
     * byte[]ת��Ϊʮ�������ַ���
     *
     * @param bytes ��Ҫת��Ϊ�ַ�����byte[]
     * @return ת�����ʮ�������ַ���
     */
    public static String byte2HexStr(byte[] bytes) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < bytes.length; n++) {
            stmp = (Integer.toHexString(bytes[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }
}