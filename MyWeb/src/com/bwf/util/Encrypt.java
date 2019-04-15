package com.bwf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//加密类：提供多种加密方式
public class Encrypt {

	//静态方法，无需实例，直接调用
	public static String toMD5(String str) throws NoSuchAlgorithmException {
		//第一步：得到一个信息摘要器 = 获取一个生成md5的对象
		MessageDigest digest = MessageDigest.getInstance("md5");
		
		//第二步:通过对象，提取一个内容的字节数组
		byte[] result = digest.digest(str.getBytes());
		
		//第三步：创建一个用于组合数据的对象，选择：StringBuffered
		//      加密过程中，禁止数据变动特别内存变动
		StringBuffer sb = new StringBuffer();
		for(byte b : result){
			
			//位运算判断整形转换后的结果
			int number=b & 0xff;
			//转换成特殊的string格式
			String s = Integer.toHexString(number);
			//转成的s，有没有长度
			if(s.length()==1){
				sb.append("0");//有长度的时候，补充一个0(保证长度一样)
			}
			sb.append(s);//追加这个转换的特殊内容
		}
        //返回结果
		return sb.toString();
	}
	
}
