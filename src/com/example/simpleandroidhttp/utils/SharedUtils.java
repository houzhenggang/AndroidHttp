package com.example.simpleandroidhttp.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedUtils {

	private static SharedUtils getshared = null;
	private static SharedPreferences settings;

	public SharedUtils(Context ctx) {
		settings = ctx.getSharedPreferences(ctx.getPackageName(), 0);
	}
//
//	public SharedUtils(Context ctx,String name) {
//		settingsForJS = ctx.getSharedPreferences(name, 0);
//	}
//	
//	public static SharedUtils getInstance(Context ctx,String name) {
//		if (null == getsharedForJS) {
//			getsharedForJS = new SharedUtils(ctx,name);
//		}
//		return getsharedForJS;
//	}

	public static SharedUtils getInstance(Context ctx) {
		if (null == getshared) {
			getshared = new SharedUtils(ctx);
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

	public int getSharedIntKey(String string) {
		return settings.getInt(string, 0);
	}

	public void setSharedIntKey(String key, int value) {
		Editor editor = settings.edit();
		editor.putInt(key, value);
		editor.commit();
	}

	public long getSharedLongKey(String str) {
		return settings.getLong(str, 0);
	}

	public void setSharedLongKey(String key, long value) {
		Editor editor = settings.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public float getSharedFloatKey(String str) {
		return settings.getFloat(str, 0);
	}

	public void setSharedFloatKey(String key, float value) {
		Editor editor = settings.edit();
		editor.putFloat(key, value);
		editor.commit();
	}

	public boolean getSharedBooleanKey(String str) {
		return settings.getBoolean(str, false);
	}

	public void setSharedBooleanKey(String key, boolean value) {
		Editor editor = settings.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}

	/**
	 * 将对象持久化到文件中
	 * 
	 * @param filePath
	 *            文件路径
	 * @param obj
	 *            对象
	 * @return
	 * @throws InterruptedException
	 */
	@SuppressWarnings("rawtypes")
	public boolean setSharedObject(String filePath, Object obj) throws IOException, InterruptedException {
		FileUtils.makeFile(filePath);
		FileUtils.chmod(filePath);
		for (Class cls : Arrays.asList(obj.getClass().getInterfaces())) {
			if (cls.equals(Serializable.class)) {
				ObjectOutputStream oout = new ObjectOutputStream(new FileOutputStream(filePath));
				oout.writeObject(obj);
				oout.close();
				return true;
			}
		}
		return false;
	}

	/**
	 * 从文件路径里读出对象
	 * 
	 * @param filePath
	 *            文件路径
	 * @param cls
	 *            对象类型
	 * @return
	 * @throws ClassNotFoundException
	 */
	public <T> T getSharedObject(String filePath, Class<T> cls) throws IOException, ClassNotFoundException {
		if (FileUtils.isFileExist(filePath)) {
			ObjectInputStream oin = new ObjectInputStream(new FileInputStream(filePath));
			T t = (T) oin.readObject(); // 没有强制转换到Person类型
			oin.close();
			return t;
		}
		return null;
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
