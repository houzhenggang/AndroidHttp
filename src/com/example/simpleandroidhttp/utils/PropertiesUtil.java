package com.example.simpleandroidhttp.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import android.content.Context;
import android.util.Log;

/**
 * @author Jelly.wen 目前是为储存配置下发用
 */
public class PropertiesUtil {

	private static final String TAG = PropertiesUtil.class.getSimpleName();

	public static Properties loadConfig(Context context, String file) {
		Properties properties = new Properties();
		try {
			FileInputStream s = new FileInputStream(file);
			properties.load(s);
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "e = " + e);
		}
		return properties;
	}

	public static void saveConfig(Context context, String file, Properties properties) {
		try {
			FileOutputStream s = new FileOutputStream(file, false);
			properties.store(s, "properties file");
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(TAG, "e = " + e);
		}
	}
}
