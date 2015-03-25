package com.example.simpleandroidhttp.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class AppUtils {

	/**
	 * AppUtils的实例。
	 */
	private static AppUtils mAppUtils;

	/**
	 * 当前手机宽度
	 */
	private static int screenWidth;
	/**
	 * 当前手机高度
	 */
	private static int screenHeigh;

	public AppUtils(Activity activity) {

		DisplayMetrics dm = new DisplayMetrics();
		// 获取屏幕信息
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeigh = dm.heightPixels;
	}

	/**
	 * 获取ImageLoader的实例。
	 * 
	 * @return ImageLoader的实例。
	 */
	public static AppUtils getInstance(Activity activity) {
		if (mAppUtils == null) {
			mAppUtils = new AppUtils(activity);
		}
		return mAppUtils;
	}

	/**
	 * 当前手机宽度
	 * 
	 * @return 如果手机分辨率是1920*1080 返回 1080
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * 当前手机高度
	 * 
	 * @return 如果手机分辨率是1920*1080 返回 1920
	 */
	public int getScreenHeigh() {
		return screenHeigh;
	}

}
