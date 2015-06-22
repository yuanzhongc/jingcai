package com.yz.util;

import java.security.MessageDigest;

/**
 * 密码加密
 * 
 * @author F.Liu
 * 
 */
public class PasswordUtil {

	/**
	 * 获得MD5加密密码的方法
	 */
	public static String encryptPwd(String pwd) {
		String origMD5 = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] result = md5.digest(pwd.getBytes());
			origMD5 = byteArray2HexStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origMD5;
	}

	/**
	 * 处理字节数组得到MD5密码的方法
	 */
	private static String byteArray2HexStr(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			sb.append(byte2HexStr(b));
		}
		return sb.toString();
	}

	/**
	 * 字节标准移位转十六进制方法
	 */
	private static String byte2HexStr(byte b) {
		String hexStr = null;
		int n = b;
		if (n < 0) {
			// 若需要自定义加密,请修改这个移位算法即可
			n = b & 0x7F + 128;
		}
		hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
		return hexStr.toUpperCase();
	}

	/**
	 * 提供一个MD5多次加密方法
	 */
	public static String encryptPwd(String pwd, int times) {
		String md5 = encryptPwd(pwd);
		for (int i = 0; i < times - 1; i++) {
			md5 = encryptPwd(md5);
		}
		return encryptPwd(md5);
	}

	/**
	 * 密码验证方法
	 */
	public static boolean verifyPwd(String pwd, String encryptedStr) {
		return encryptPwd(pwd).equals(encryptedStr);
	}

	/**
	 * 重载一个多次加密时的密码验证方法
	 */
	public static boolean verifyPwd(String pwd, String encryptedStr, int times) {
		return encryptPwd(pwd, times).equals(encryptedStr);
	}

	public static void main(String[] args) {
		System.out.println(encryptPwd("colin", 5));
	}

}
