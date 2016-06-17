package com.lx.util;


public class StringUtils extends org.apache.commons.lang.StringUtils {
	public static char TEN = '十';

	public static void main(String[] args) {
		String aString="    ww   w    w  w    ";

//		String title = "😁5😁😁😁Llfldakf;dsk。f😁😁😁😁😁😁daslfjdsa;lfkjdsd'j'l'f'k'd'j'sa'l'k";
//        System.out.println(removeFourChar(title));
		System.out.println(trim(aString));
	}

	/**
	 * 截取指定长度的半角字符,以半角字符长度为单位
	 *
	 * @param str
	 *            输入字符串
	 * @param length
	 *            固定的长度，半角字符长度按1计数，全角字符按2计数
	 * @return
	 */
	public static String fitLength(String str, int length) {
		if (str == null || length >= (str.length() << 1)) {
			return str;
		}
		int tmpLength = 0;
		int i = 0;
		for (; tmpLength < length && i < str.length(); i++) {
			char c = str.charAt(i);
			if (CharUtils.isHalfCase(c)) {
				tmpLength += 1;
			} else {
				tmpLength += 2;
			}
		}
		return str.substring(0, i);
	}

	public static String firstLetterToUpperCase(String str) {
		if (str == null) {
			return str;
		}
		char upperCase = Character.toUpperCase(str.charAt(0));
		return (String) (str.length() > 1 ? upperCase + str.substring(1) : upperCase);
	}

	public static String sub(String str, int length) {
		if (str == null) {
			return str;
		}
		str = str.trim();
		if (str.length() <= length) {
			return str;
		}
		return str.substring(0, length);
	}

	/**
	 * 计算字符串str转换成半角字符后的长度
	 *
	 * @param str
	 * @return
	 */
	public static int halfCaseLength(String str) {
		int length = 0;
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return length;
		}
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (CharUtils.isHalfCase(c)) {
				length += 1;
			} else {
				length += 2;
			}
		}
		return length;
	}

	/**
	 * 根据原图url获取scale(比如197x136)缩略图url
	 *
	 * @param url
	 * @param scale
	 * @return
	 */
	public static String getThumbnail(String url, String scale) {
		int index = url.lastIndexOf(".");
		if (index == -1) {
			return url;
		}
		scale = "_" + scale;
		return url.substring(0, index) + scale + url.substring(index);
	}

	/**
	 * 根据url获取该url所属的group
	 *
	 * @param url
	 * @return
	 */
	public static String getGroup(String url) {
		if (org.apache.commons.lang.StringUtils.isBlank(url)) {
			return null;
		}
		int start = url.indexOf("group");
		int end = url.indexOf("/", start);
		return url.substring(start, end);
	}

	/**
	 * 替换最后一个点号后面的字符串为*
	 *
	 * @param remoteAddr
	 * @return
	 */
	public static String mosaic(String str) {
		if (str == null) {
			return str;
		}
		int lastIndex = str.lastIndexOf(".");
		if (lastIndex == -1) {
			return str;
		}

		return str.substring(0, lastIndex) + ".*";
	}

	/**
	 * 从字符串中提取数值,包括中文数字
	 *
	 * @param title
	 * @return
	 */
	public static Integer getNumber(String str) {
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return null;
		}
		Integer number = null;
		StringBuilder numberStr = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			int length = numberStr.length();
			Integer digit = StringUtils.getDigit(i, str);
			if (digit != null) {
				numberStr.append(digit);
			} else if (length > 0 && TEN != str.charAt(i)) {
				number = Integer.valueOf(numberStr.toString());
				numberStr.delete(0, length);
			}
		}
		if (numberStr.length() > 0) {
			number = Integer.valueOf(numberStr.toString());
		}
		return number != null ? number : 0;
	}

	/**
	 * 如果str的at位置是数字返回数字(包括大写数字等)，否则返回空
	 *
	 * @param position
	 * @param str
	 * @return
	 */
	public static Integer getDigit(int position, String str) {
		char ch = str.charAt(position);
		if (TEN == ch) {
			int previousIndex = position - 1;
			int nextIndex = position + 1;
			Integer previous = previousIndex >= 0 ? DigitUtils.getDigitInSameType(TEN, str.charAt(previousIndex))
					: null;
			Integer next = nextIndex < str.length() ? DigitUtils.getDigitInSameType(TEN, str.charAt(nextIndex))
					: null;
			if (previous == null && next == null) {
				return 10;
			}
			if (previous == null && next != null) {
				return 1;
			}
			if (previous != null && next == null) {
				return 0;
			}
			if (previous != null && next != null) {
				return null;
			}
		}
		return DigitUtils.getDigit(ch);
	}

	/**
	 * @param str
	 * @return
	 */
	public static String removeNumber(String str) {
		if (org.apache.commons.lang.StringUtils.isBlank(str)) {
			return str;
		}
		StringBuilder temp = new StringBuilder(str);
		int length = temp.length();
		for (int j = 0; j < length;) {
			char c = temp.charAt(j);
			if (DigitUtils.isDigit(c)) {
				temp.deleteCharAt(j);
				length = length - 1;
			} else {
				j++;
			}
		}
		return temp.toString();
	}

	/**
	 * 截取str字符串中从第一个open到第一个close之间的字符串
	 *
	 * @param str
	 * @param open
	 * @param close
	 * @return
	 */
	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		int start = str.indexOf(open);
		if (start == INDEX_NOT_FOUND) {
			start = 0;
		} else {
			start = start + open.length();
		}
		int end = str.indexOf(close, start);
		if (end != INDEX_NOT_FOUND) {
			return str.substring(start, end);
		} else if (start > 0) {
			return str.substring(start);
		}
		return str;
	}

	public static String wrap(Object str, String sign) {
		return sign + str + sign;
	}

	/**
	 * 检测是否有emoji字符
	 *
	 * @param source
	 * @return 一旦含有就抛出
	 */
	public static boolean containsEmoji(String source) {
		if (StringUtils.isBlank(source)) {
			return false;
		}
		int len = source.length();

		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);

			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}
		return false;
	}

	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 *
	 * @param content
	 * @return
	 */
	public static String removeFourChar(String content) {
		byte[] conbyte = content.getBytes();
		for (int i = 0; i < conbyte.length; i++) {
			if ((conbyte[i] & 0xF8) == 0xF0) {
				for (int j = 0; j < 4; j++) {
					conbyte[i + j] = 0x30;
				}
				i += 3;
			}
		}
		content = new String(conbyte);
		return content.replaceAll("0000", "");
	}

	public static String trim(String str){
		String res="";
		if(isBlank(str)){
			return res;
		}
		res=str.replaceAll("\\s*", "");
		return res;
	}
}
