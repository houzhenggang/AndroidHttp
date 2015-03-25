package com.example.simpleandroidhttp.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

/**
 * @author Jelly.wen 负责启动activity service 等
 */
public class AppStartUtil {

	/**
	 * @param mContext
	 * @param targetActivity
	 *            例：AppStartUtil.startActivity(MainActivity.this,
	 *            LoginActivity.class.getName());
	 */
	public static void startActivity(Context mContext, String targetActivity) {
		Intent intent = new Intent();
		ComponentName componentName = new ComponentName(mContext, targetActivity);
		intent.setComponent(componentName);
		intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		mContext.startActivity(intent);
	}

	public static void startActivity(Context mContext, String targetActivity, int flag) {
		Intent intent = new Intent();
		ComponentName componentName = new ComponentName(mContext, targetActivity);
		intent.setComponent(componentName);
		intent.setFlags(flag);
		mContext.startActivity(intent);
	}

	public static void startActivityForResult(Activity activity, String targetActivity, int requestCode, Intent intent) {
		ComponentName componentName = new ComponentName(activity, targetActivity);
		intent.setComponent(componentName);
		intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void startActivityForResult(Activity activity, String targetActivity, int requestCode) {
		Intent intent = new Intent();
		ComponentName componentName = new ComponentName(activity, targetActivity);
		intent.setComponent(componentName);
		intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		activity.startActivityForResult(intent, requestCode);
	}

	public static void startActivity(Context mContext, String targetActivity, Intent intent) {
		ComponentName componentName = new ComponentName(mContext, targetActivity);
		intent.setComponent(componentName);
		intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
		mContext.startActivity(intent);
	}
}
