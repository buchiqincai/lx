package com.lx.util;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public class ArrayUtils extends org.apache.commons.lang.ArrayUtils {

	/**
	 * 两个数组能是否有任意一个相同的元素
	 *
	 * @param array1
	 * @param array2
	 * @return
	 */
	public static boolean containSameOne(Object[] array1, Object[] array2) {
		Arrays.sort(array1);
		for (int i = 0; i < array2.length; i++) {
			if (Arrays.binarySearch(array1, array2[i]) >= 0)
				return true;
		}
		return false;
	}

	/**
	 * 去除数组内每个元素的前后空格
	 *
	 * @param array
	 * @return
	 */
	public static String[] trimElement(String[] array) {
		if (array == null) {
			return array;
		}
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i].trim();
		}
		return array;
	}

	/**
	 * referer前缀是否包含在array中
	 *
	 * @param referer
	 * @param array
	 * @return
	 */
	public static boolean containPrefix(String referer, String[] array) {
		if (StringUtils.isBlank(referer) || isEmpty(array)) {
			return false;
		}
		for (String element : array) {
			if (referer.startsWith(element)) {
				return true;
			}
		}
		return false;
	}
}
