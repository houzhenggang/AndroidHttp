package com.example.simpleandroidhttp.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.example.simpleandroidhttp.R;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.view.WindowManager;


/**
 * 检测网络
 * 
 * @author Jelly.wen
 * 
 */
public class NetworkUtil {

	/** 网络不可用 */
	public static final int NONETWORK = 0;
	/** 是wifi连接 */
	public static final int WIFI = 1;
	/** 不是wifi连接 */
	public static final int NOWIFI = 2;

	/**
	 * 检验网络连接 并判断是否是wifi连接
	 * 
	 * @param context
	 * @return <li>没有网络：Network.NONETWORK;</li> <li>wifi 连接：Network.WIFI;</li>
	 *         <li>mobile 连接：Network.NOWIFI</li>
	 */
	public static int checkNetWorkType(Context context) {

		if (!isNetworkAvailable(context)) {
			return NetworkUtil.NONETWORK;
		}
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		// cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting())
			return NetworkUtil.WIFI;
		else
			return NetworkUtil.NOWIFI;
	}

	/**
	 * 检测网络是否连接
	 * 
	 * @param context
	 * @return true, 可用； false， 不可用
	 */
	public static boolean isNetworkAvailable(Context context) {
		// 1.获得连接设备管理器
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null) {
			return false;
		}
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null || !ni.isAvailable()) {
			// 再次ping 确认是没有网络
			return ping();
		}
		return true;
	}

	/**
	 * @return 有网返回true 无网返回false
	 */
	private static final boolean ping() {
		try {
			String ip = "www.baidu.com";// 除非百度挂了，否则用这个应该没问题~
			// -c 是次数 -w是超时时间 秒为单位
			Process p = Runtime.getRuntime().exec("ping -c 1 -w 1 " + ip);
			// 读取ping的内容，可不加。
			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer stringBuffer = new StringBuffer();
			String content = "";
			while ((content = in.readLine()) != null) {
				stringBuffer.append(content);
			}
			// PING的状态
			int status = p.waitFor();
			if (status == 0) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 设置网络
	 * 
	 * @param mContext
	 */
	public static void showSetingNetDialog(final Context mContext) {
		AlertDialog mDialog = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle("net_error");
		builder.setMessage("please check network !");
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				Intent intent = new Intent(Settings.ACTION_SETTINGS);
				mContext.startActivity(intent);
			}
		});
		builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();

			}
		});

		builder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				// 退出
				android.os.Process.killProcess(android.os.Process.myPid());
				System.exit(1);
			}
		});
		mDialog = builder.create();
		mDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
		mDialog.show();
	}
}
