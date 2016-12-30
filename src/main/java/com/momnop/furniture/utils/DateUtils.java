package com.momnop.furniture.utils;

import java.util.Calendar;

public class DateUtils {
	public static boolean isChristmas() {
		Calendar calendar = Calendar.getInstance();

        if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26)
        {
            return true;
        }
        return false;
	}
}
