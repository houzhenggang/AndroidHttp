package com.example.simpleandroidhttp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.simpleandroidhttp.R;


import android.content.Context;


/**
 * @author Jelly.wen 时间用到的公共方法
 */
public class DateUtils {

	/**
	 * @param pattern
	 *            如： yyyy年MM月dd日 HH:mm:ss HH:mm:ss
	 * @return
	 */
	public static String getDate(String pattern) {
		// SimpleDateFormat formatter = new SimpleDateFormat
		// ("yyyy年MM月dd日 HH:mm:ss");
		// SimpleDateFormat formatter = new SimpleDateFormat ("HH:mm:ss");
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);

		return str;
	}

	public static String getWeek(Context mContext) {
		Calendar calendar = Calendar.getInstance();
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		String[] today = mContext.getResources().getStringArray(R.array.week);
		return today[day - 1];
	}

}
