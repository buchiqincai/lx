package com.lx.util;

import java.util.HashMap;
import java.util.Map;

public class DigitUtils {
	public static Map<Character, Integer> digiMap1 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap2 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap3 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap4 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap5 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap6 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap7 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap8 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap9 = new HashMap<Character, Integer>();
	public static Map<Character, Integer> digiMap10 = new HashMap<Character, Integer>();
	static {
		digiMap1.put('0', 0);
		digiMap1.put('1', 1);
		digiMap1.put('2', 2);
		digiMap1.put('3', 3);
		digiMap1.put('4', 4);
		digiMap1.put('5', 5);
		digiMap1.put('6', 6);
		digiMap1.put('7', 7);
		digiMap1.put('8', 8);
		digiMap1.put('9', 9);

		digiMap2.put('零', 0);
		digiMap2.put('一', 1);
		digiMap2.put('二', 2);
		digiMap2.put('三', 3);
		digiMap2.put('四', 4);
		digiMap2.put('五', 5);
		digiMap2.put('六', 6);
		digiMap2.put('七', 7);
		digiMap2.put('八', 8);
		digiMap2.put('九', 9);
		digiMap2.put('十', 10);

		digiMap3.put('壹', 1);
		digiMap3.put('貳', 2);
		digiMap3.put('叁', 3);
		digiMap3.put('肆', 4);
		digiMap3.put('伍', 5);
		digiMap3.put('陆', 6);
		digiMap3.put('柒', 7);
		digiMap3.put('捌', 8);
		digiMap3.put('玖', 9);

		digiMap4.put('①', 1);
		digiMap4.put('②', 2);
		digiMap4.put('③', 3);
		digiMap4.put('④', 4);
		digiMap4.put('⑤', 5);
		digiMap4.put('⑥', 6);
		digiMap4.put('⑦', 7);
		digiMap4.put('⑧', 8);
		digiMap4.put('⑨', 9);
		digiMap4.put('⑩', 10);

		digiMap5.put('Ⅰ', 1);
		digiMap5.put('Ⅱ', 2);
		digiMap5.put('Ⅲ', 3);
		digiMap5.put('Ⅳ', 4);
		digiMap5.put('Ⅴ', 5);
		digiMap5.put('Ⅵ', 6);
		digiMap5.put('Ⅶ', 7);
		digiMap5.put('Ⅷ', 8);
		digiMap5.put('Ⅸ', 9);
		digiMap5.put('Ⅹ', 10);
		digiMap5.put('Ⅺ', 11);
		digiMap5.put('Ⅻ', 12);

		digiMap6.put('⑴', 1);
		digiMap6.put('⑵', 2);
		digiMap6.put('⑶', 3);
		digiMap6.put('⑷', 4);
		digiMap6.put('⑸', 5);
		digiMap6.put('⑹', 6);
		digiMap6.put('⑺', 7);
		digiMap6.put('⑻', 8);
		digiMap6.put('⑼', 9);
		digiMap6.put('⑽', 10);

		digiMap7.put('㈠', 1);
		digiMap7.put('㈡', 2);
		digiMap7.put('㈢', 3);
		digiMap7.put('㈣', 4);
		digiMap7.put('㈣', 5);
		digiMap7.put('㈥', 6);
		digiMap7.put('㈦', 7);
		digiMap7.put('㈧', 8);
		digiMap7.put('㈨', 9);
		digiMap7.put('㈩', 10);

		digiMap8.put('⑴', 1);
		digiMap8.put('⑵', 2);
		digiMap8.put('⑶', 3);
		digiMap8.put('⑷', 4);
		digiMap8.put('⑸', 5);
		digiMap8.put('⑹', 6);
		digiMap8.put('⑺', 7);
		digiMap8.put('⑻', 8);
		digiMap8.put('⑼', 9);
		digiMap8.put('⑽', 10);
		digiMap8.put('⑾', 11);
		digiMap8.put('⑿', 12);
		digiMap8.put('⒀', 13);
		digiMap8.put('⒁', 14);
		digiMap8.put('⒂', 15);
		digiMap8.put('⒃', 16);
		digiMap8.put('⒄', 17);
		digiMap8.put('⒅', 18);
		digiMap8.put('⒆', 19);
		digiMap8.put('⒇', 20);

		digiMap9.put('⒈', 1);
		digiMap9.put('⒉', 2);
		digiMap9.put('⒊', 3);
		digiMap9.put('⒋', 4);
		digiMap9.put('⒌', 5);
		digiMap9.put('⒍', 6);
		digiMap9.put('⒎', 7);
		digiMap9.put('⒏', 8);
		digiMap9.put('㈨', 9);
		digiMap9.put('㈩', 10);
		digiMap9.put('⒒', 11);
		digiMap9.put('⒓', 12);
		digiMap9.put('⒔', 13);
		digiMap9.put('⒕', 14);
		digiMap9.put('⒖', 15);
		digiMap9.put('⒗', 16);
		digiMap9.put('⒘', 17);
		digiMap9.put('⒙', 18);
		digiMap9.put('⒚', 19);
		digiMap9.put('⒛', 20);

		digiMap10.put('０', 0);
		digiMap10.put('１', 1);
		digiMap10.put('２', 2);
		digiMap10.put('３', 3);
		digiMap10.put('４', 4);
		digiMap10.put('５', 5);
		digiMap10.put('６', 6);
		digiMap10.put('７', 7);
		digiMap10.put('８', 8);
		digiMap10.put('９', 9);

	}

	public static Integer getDigit(char ch) {
		Integer digit = digiMap1.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap2.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap3.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap4.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap5.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap6.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap7.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap8.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap9.get(ch);
		if (digit != null) {
			return digit;
		}
		digit = digiMap10.get(ch);
		if (digit != null) {
			return digit;
		}
		return null;
	}

	public static Integer getDigitInSameType(char refer, char ch) {
		if (digiMap1.containsKey(refer)) {
			return digiMap1.get(ch);
		}
		if (digiMap2.containsKey(refer)) {
			return digiMap2.get(ch);
		}
		if (digiMap3.containsKey(refer)) {
			return digiMap3.get(ch);
		}
		if (digiMap4.containsKey(refer)) {
			return digiMap4.get(ch);
		}
		if (digiMap5.containsKey(refer)) {
			return digiMap5.get(ch);
		}
		if (digiMap6.containsKey(refer)) {
			return digiMap6.get(ch);
		}
		if (digiMap7.containsKey(refer)) {
			return digiMap7.get(ch);
		}
		if (digiMap8.containsKey(refer)) {
			return digiMap8.get(ch);
		}
		if (digiMap9.containsKey(refer)) {
			return digiMap9.get(ch);
		}
		if (digiMap10.containsKey(refer)) {
			return digiMap10.get(ch);
		}
		return null;
	}

	public static boolean isDigit(char refer) {
		if (digiMap1.containsKey(refer)) {
			return true;
		}
		if (digiMap2.containsKey(refer)) {
			return true;
		}
		if (digiMap3.containsKey(refer)) {
			return true;
		}
		if (digiMap4.containsKey(refer)) {
			return true;
		}
		if (digiMap5.containsKey(refer)) {
			return true;
		}
		if (digiMap6.containsKey(refer)) {
			return true;
		}
		if (digiMap7.containsKey(refer)) {
			return true;
		}
		if (digiMap8.containsKey(refer)) {
			return true;
		}
		if (digiMap9.containsKey(refer)) {
			return true;
		}
		if (digiMap10.containsKey(refer)) {
			return true;
		}
		return false;
	}
}
