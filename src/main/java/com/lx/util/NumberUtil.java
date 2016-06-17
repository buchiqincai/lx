package com.lx.util;

import java.util.Random;
import java.util.UUID;

/**
 * 随机数工具类
 * @author lx
 *
 */
public class NumberUtil {
	
	/**
	 * 获取指定长度的随机字符窜，数字和字母混合
	 * @param length 字符窜长度
	 * @return
	 */
	public static String getCharAndNumber(int length){
		String val="";
		if (length <=0) {
			return val;
		}
		Random random=new Random();
		for(int i=0;i<length;i++){
			//去数字还是字母
			String charOrNumber=random.nextInt(2) % 2 == 0 ? "c":"n";
			//字符窜
			if("c".equals(charOrNumber)){
				//取大写还是小写字母
				int choice=random.nextInt(2) % 2 == 0 ? 65:97;
				val +=(char)(choice+random.nextInt(26));
			}
			//数字
			if ("n".equals(charOrNumber)) {
				val +=String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}

	/**
	 * 获取uuid
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
