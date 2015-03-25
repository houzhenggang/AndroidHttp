package com.example.simpleandroidhttp;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.cookie.BasicClientCookie;

import com.example.simpleandroidhttp.utils.CrashHandler;
import com.example.simpleandroidhttp.utils.FileUtils;
import com.example.simpleandroidhttp.utils.SharedUtils;
import com.example.simpleandroidhttp.utils.ToastUtils;
import com.example.simpleandroidhttp.utils.ZozoLog;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Handler;
import android.telephony.TelephonyManager;

public class SimpleAndroidHttpApplication extends Application {


	private static final String TAG = SimpleAndroidHttpApplication.class.getSimpleName();

	public static String LOGTAG = SimpleAndroidHttpApplication.class.getSimpleName();

	/**
	 * application的单例模式
	 */
	private static SimpleAndroidHttpApplication instance = new SimpleAndroidHttpApplication()  ;
	// /**

	// * 程序的Context
	// */
	// private static Context appContext;
	//
	/**
	 * 本程序所有继承baseActivity的activity都会加入此list，程序退出时方便全部清理
	 */
	private List<Activity> mActivityList = new LinkedList<Activity>();
	public static BasicCookieStore cookieStore = new BasicCookieStore();
	public static String deviceId;
	public static File CONFIG_SAVE_FILE = null;
	private static Properties prop = null;

	/**
	 * toast 用
	 */
	private Handler handler = new Handler();

	@Override
	public void onCreate() {
		super.onCreate();
		beforeOnCreate();
		initdata();

	}

	/**
	 * 每次启动只需初始化一次的数据
	 */
	private void initdata() {

		deviceId = getDeviceId();
		SharedUtils.getInstance(instance).setSharedStringKey("device_id", deviceId);
		try {
			CONFIG_SAVE_FILE = FileUtils.makeFile(getFilesDir() + File.separator + "Properties" + File.separator + "configDetail.txt");
			FileUtils.chmod(CONFIG_SAVE_FILE.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
			ToastUtils.showToast("创建 Properties 文件失败，程序要退出！");
			exit();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ToastUtils.showToast("更改 Properties 文件权限，程序要退出！");
			exit();
		}

		// 捕获程序异常
		CrashHandler catchHandler = CrashHandler.getInstance();
		catchHandler.init(getApplicationContext());

		handler = new Handler();

		BasicClientCookie clientCookie = new BasicClientCookie("device_id", deviceId);
		clientCookie.setDomain(Constants.DOMAIN);
		cookieStore.addCookie(clientCookie);
		String cookies = cookieStore.getCookies().toString();
	}

	/**
	 * oncreate之前初始化一些东西
	 */
	private void beforeOnCreate() {
		instance = SimpleAndroidHttpApplication.this;
		// appContext = LockScreenApplication.this.getApplicationContext();
	}

	public Handler getHandler() {
		return handler;
	}

	/**
	 * 获取 Application的单例模式
	 * 
	 * @return LockScreenApplication
	 */
	public static SimpleAndroidHttpApplication getInstance() {

		return instance;
	}

	// /**
	// * 获取全局Context
	// * @return Context
	// */
	// public Context getApplicationContext(){
	//
	// if(appContext == null){
	// appContext = instance.getApplicationContext();
	// }
	//
	// return appContext;
	// }

	/**
	 * 集成于baseactivity 的都加进来方便关闭
	 * 
	 * @param activity
	 */
	public void addActivity(Activity activity) {
		mActivityList.add(activity);
		// 以下为测试用
		ZozoLog.d(TAG, "当前加入的activity数 = " + mActivityList.size());
		for (int i = 0; i < mActivityList.size(); i++) {
			ZozoLog.d(TAG, " " + mActivityList.get(i).getClass().getSimpleName());
		}
	}

	/**
	 * 程序退出时调用
	 */
	public void exit() {
		try {
			for (Activity activity : mActivityList) {
				if (activity != null)
					activity.finish();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}

	/**
	 * 应用是否处于debug模式 可以通过manifest里的application中的 android:debuggable="true"来开关
	 * 
	 * @return 是返回true 不是返回false
	 * 
	 */
	public static boolean isDebugMode() {
		ApplicationInfo info = getInstance().getApplicationInfo();
		return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
	}

	/**
	 * 获取手机的设备号
	 * 
	 * @return 以字符串形式返回设备id
	 */
	public static String getDeviceId() {
		return ((TelephonyManager) getInstance().getSystemService(TELEPHONY_SERVICE)).getDeviceId();

	}




}
