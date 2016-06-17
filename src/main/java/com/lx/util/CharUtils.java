package com.lx.util;

public class CharUtils extends org.apache.commons.lang.CharUtils {
	/**
	 * 是否为半角
	 * 
	 * @param c
	 *            字符
	 * @return true：半角； false：全角
	 */
	public static boolean isHalfCase(char c) {
		// 基本拉丁字母（即键盘上可见的，空格、数字、字母、符号）
		if (c >= 32 && c <= 127) {
			return true;
		}
		// 日文半角片假名和符号
		else if (c >= 65377 && c <= 65439) {
			return true;
		}
		return false;
	}

}
