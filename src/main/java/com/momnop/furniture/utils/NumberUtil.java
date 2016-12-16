package com.momnop.furniture.utils;

public class NumberUtil {
	public static void test() {
		for (int i = 0; i < 18; i++) {
			System.out.println(getByte(i));
		}
	}
	
	public static String getByte(int num) {
	   return "0x" + Integer.toHexString(num);
	}
}
