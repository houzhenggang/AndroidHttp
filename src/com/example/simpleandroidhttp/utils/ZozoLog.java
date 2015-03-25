package com.example.simpleandroidhttp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import com.example.simpleandroidhttp.Constants;


import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;


public class ZozoLog {

	private static final String LOG_PATH = Environment.getExternalStorageState() + "/DaishuCourier";
	private static ZozoLog sInstance;
	private static boolean debug = true;
	private static boolean developmentModel = true;

	private ZozoLog() {
	}

	public static synchronized final ZozoLog getInstance() {
		if (sInstance == null) {
			sInstance = new ZozoLog();
		}
		return sInstance;
	}

	public static void setDebugMode(boolean mode) {
		debug = mode;
	}

	public static void v(String tag, String msg) {
		if (developmentModel) {
			Log.v(tag, msg);
		} else if (debug) {
			Log.v(Constants.TAG.PACKAGETAG, tag + ":" + msg);
		}
	}

	public static void w(String tag, String msg) {
		if (developmentModel) {
			Log.w(tag, msg);
		} else if (debug) {
			Log.w(Constants.TAG.PACKAGETAG, tag + ":" + msg);
		}
	}

	public static void i(String tag, String msg) {
		if (developmentModel) {
			Log.i(tag, msg);
		} else if (debug) {
			Log.i(Constants.TAG.PACKAGETAG, tag + ":" + msg);
		}
	}

	public static void d(String tag, String msg) {
		if (developmentModel) {
			Log.d(tag, msg);
		} else if (debug) {
			Log.d(Constants.TAG.PACKAGETAG, tag + ":" + msg);
		}
	}

	public static void e(String tag, String msg) {
		if (developmentModel) {
			Log.e(tag, msg);
		} else if (debug) {
			Log.e(Constants.TAG.PACKAGETAG, tag + ":" + msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (debug) {
			Log.e(Constants.TAG.PACKAGETAG, tag + ":" + msg, tr);
		}
	}

	private static HashMap<String, Integer> actionMap = new HashMap<String, Integer>();

	public static void a(Class<?> clazz, String method, MotionEvent event, String log) {
		a(clazz, method, event, log, true);
	}

	public static void a(Class<?> clazz, String method, MotionEvent event, String log, boolean ignoreDuplicate) {
		if (!debug || event == null)
			return;
		int action = event.getAction();
		String calssName = clazz == null ? "" : clazz.getSimpleName();
		String key = calssName + method;
		Integer i = actionMap.get(key);
		int value = -10;
		if (i != null)
			value = i;
		if (action != value)
			actionMap.put(key, action);
		if (ignoreDuplicate && action == value)
			return;
		StringBuilder sb = new StringBuilder();
		sb.append("class : " + calssName);
		sb.append(TextUtil.isEmpty(calssName) ? "" : ", method : " + method);
		sb.append(", action : " + getAction(action));
		sb.append(TextUtil.isEmpty(log) ? "" : ", log : " + log);
		Log.d("ActionTracker", sb.toString());
	}

	public static void a(Class<?> clazz, String method, MotionEvent event) {
		a(clazz, method, event, "");
	}

	public static void k(Class<?> clazz, String method, int keyCode, KeyEvent event) {
		k(clazz, method, keyCode, event, "");
	}

	public static void k(Class<?> clazz, String method, int keyCode, KeyEvent event, String log) {
		if (!debug) {
			return;
		}

		String calssName = clazz == null ? "" : clazz.getSimpleName();
		StringBuilder sb = new StringBuilder();
		sb.append("class : " + calssName);
		sb.append(TextUtil.isEmpty(calssName) ? "" : ", method : " + method);
		sb.append(", keycode : " + keyCode);
		if (event != null) {
			int action = event.getAction();
			sb.append(", action : " + getAction(action));
		}
		sb.append(TextUtil.isEmpty(log) ? "" : ", log : " + log);
		Log.d("KeyEventTracker", sb.toString());

	}

	public static String getAction(int action) {
		switch (action) {
		case MotionEvent.ACTION_CANCEL:
			return "ACTION_CANCEL";
		case MotionEvent.ACTION_UP:
			return "ACTION_UP";
		case MotionEvent.ACTION_DOWN:
			return "ACTION_DOWN";
		case MotionEvent.ACTION_MOVE:
			return "ACTION_MOVE";
		case MotionEvent.ACTION_OUTSIDE:
			return "ACTION_OUTSIDE";
			// case MotionEvent.ACTION_SCROLL:
			// return "ACTION_SCROLL";
		}
		return null;
	}

	public static void clearCache() {
		File logFile = new File(LOG_PATH + "/log4pad.txt");
		if (logFile.exists()) {
			logFile.delete();
		}
	}

	public static void writeToCache(String filter, String msg) {
		if (msg.contains(filter)) {
			FileOutputStream fos = null;
			try {
				File logFile = new File(LOG_PATH);
				if (!logFile.exists()) {
					logFile.mkdirs();
				}
				fos = new FileOutputStream(logFile + "/log4pad.txt", true);
				fos.write(msg.getBytes());
				fos.write("\n\r".getBytes());
				fos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					fos = null;
				}
			}
		}
	}

	public static void trace() {
		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		for (StackTraceElement stackTraceElement : traces) {
			ZozoLog.d("Trace", stackTraceElement.toString());
		}
	}

	public static String getTraceInfo() {
		StringBuffer sb = new StringBuffer();
		StackTraceElement[] stacks = new Throwable().getStackTrace();
		int stacksLen = stacks.length;
		sb.append("class: ").append(stacks[1].getClassName()).append("; method: ").append(stacks[1].getMethodName()).append("; number: ").append(stacks[1].getLineNumber());
		return sb.toString();
	}

}