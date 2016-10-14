package com.aotuo.vegetable.util;

public class Log {
	
	private final static boolean DEBUG = true;//为false不输出日志

    public static void i(String key, String value) {
        if (DEBUG) {
            android.util.Log.i(key, value);
        }

    }

    public static void e(String key, String value) {
        if (DEBUG) {
            android.util.Log.e(key, value);
        }

    }

    public static void d(String key, String value) {
        if (DEBUG) {
            android.util.Log.d(key, value);
        }

    }
}
