package com.example.simpleandroidhttp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedUtilForJS {
	private static SharedUtilForJS getshared = null;
	private static SharedPreferences settings;


	public SharedUtilForJS(Context ctx,String name) {
		settings = ctx.getSharedPreferences(name, 0);
	}
	
	public static SharedUtilForJS getInstance(Context ctx) {
		if (null == getshared) {
			getshared = new SharedUtilForJS(ctx,"storage");
		}
		return getshared;
	}

	
	public String getSharedStringKey(String string) {
		return settings.getString(string, null);
	}

	public void setSharedStringKey(String key, String value) {
		Editor editor = settings.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	
	/**
	 * 清空sharedPreferece
	 * @return
	 */
	public boolean clearAll(){
		return settings.edit().clear().commit();
	}
	
	public void removeSharedStringKey(String key){
		Editor editor = settings.edit();
		editor.remove(key);
		editor.commit();
	}
	
}
