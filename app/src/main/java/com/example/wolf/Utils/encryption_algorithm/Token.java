package com.example.wolf.Utils.encryption_algorithm;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Create by CWbl 2018-05-09
 * 接口基类
 */
public class Token
{
	/**
	 * 获取客户token
	 * @param uid 客户ID
	 * @return token
	 */
	public String getToken(Integer uid)
	{
		//return EncryptDecodeUtils.getMD5(uid + Project.getToken());
		return getMD5(uid + "c7d45036a47d01f531a47cf69b38d05c");
	}

	public static String getMD5(String s)
	{
		if (s.isEmpty())
			return null;

		char hexDigits[] = {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f'
		};
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(s.getBytes("utf-8"));

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return null;
		}
		byte[] md = md5.digest();
		char str[] = new char[md.length * 2];
		int k = 0;
		for (byte byte0 : md)
		{
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
}
