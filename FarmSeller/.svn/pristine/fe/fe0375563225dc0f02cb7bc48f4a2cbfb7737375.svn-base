package com.aotuo.vegetable.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckedIsPhoneNumUtil {
	public static boolean checkphone(String phone){
		Pattern p=Pattern.compile("^1[3|4|5|7|8][0-9]\\d{8}$");
		Matcher m=p.matcher(phone);
		return m.matches();
	}
}
