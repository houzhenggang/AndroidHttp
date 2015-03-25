package com.example.simpleandroidhttp.activity;


import com.example.simpleandroidhttp.SimpleAndroidHttpApplication;
import com.example.simpleandroidhttp.utils.NetworkUtil;
import com.example.simpleandroidhttp.utils.ZozoLog;
import com.example.simpleandroidhttp.utils.httpmanager.IHttpReqCallback;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


public abstract class BaseActivity extends Activity implements IHttpReqCallback, OnClickListener {

	private static final String TAG = BaseActivity.class.getSimpleName();
	private static Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		beforeCreate();
		ZozoLog.i(TAG, "==== onCreate ====");
		mContext = this;

		SimpleAndroidHttpApplication appInstance =SimpleAndroidHttpApplication.getInstance();
		/**
		 * 将所有activity加入管理
		 */
		appInstance.addActivity(this);
		// MobclickAgent.setDebugMode(true);
		// MobclickAgent.updateOnlineConfig(BaseActivity.this);
		findViews();
		initDataOnCreate();
		registerListener();
	}

	@Override
	public void onResume() {
		super.onResume();
		ZozoLog.i(TAG, "==== onResume ====");
		// MobclickAgent.onPageStart(mContext.getClass().getSimpleName());
		// MobclickAgent.onResume(mContext);
		// ZozoLog.d(TAG, "当前页面 = "+mContext.getClass().getSimpleName());

		initDataOnResume();
		initView();
	}

	@Override
	public void onPause() {
		super.onPause();
		ZozoLog.i(TAG, "==== onPause ====");
		// MobclickAgent.onPageEnd(mContext.getClass().getSimpleName());
		// MobclickAgent.onPause(mContext);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ZozoLog.i(TAG, "==== onDestroy ====");
	}

	public static Context getmContext() {

		return mContext;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		if (imm != null && this.getCurrentFocus() != null) {
			imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void onHttpReqDataError(int what) {
		Toast.makeText(this, "对不起,服务返回数据异常", 2).show();
	}

	@Override
	public void onHttpReqConnFail(int what) {
		Toast.makeText(this, "网络连接异常,请检查您的网络!", 2).show();
	}

	@Override
	public void onHttpReqTimeout(int what) {
		Toast.makeText(this, "网络连接超,请稍后再试!", 2).show();
	}

	@Override
	public void onHttpReqServerRefused(int what) {
		Toast.makeText(this, "服务器拒绝响应,请稍后再试!", 2).show();
	}

	@Override
	public void onHttpReqConnSuccess(String result, int what) {
		ZozoLog.i(TAG, "onHttpReqConnSuccess: what = " + what);
		try {
			if (result == null) {
				return;
			} else {
				ZozoLog.i(TAG, "onHttpReqConnSuccess: result = " + result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ZozoLog.w(TAG, "mDialog ------e.getMessage()----> " + e.getMessage());
		}
		ZozoLog.i(TAG, "onHttpReqEnd : finish = " + what);

	}

	@Override
	public boolean isNetworkAvailable(int what) {
		return NetworkUtil.isNetworkAvailable(mContext);
	}

	/**
	 * 请求开始
	 */
	@Override
	public void onHttpReqStart(int what) {
		ZozoLog.i(TAG, "onHttpReqStart : start = " + what);
	}

	/**
	 * 请求结束
	 */
	@Override
	public void onHttpReqEnd(int what) {

		ZozoLog.i(TAG, "onHttpReqEnd : finish = " + what);
	}

	/**
	 * 请求失败
	 */
	@Override
	public void onHttpReqCancelled(int what) {
		ZozoLog.i(TAG, "onHttpReqCancelled : cancel = " + what);
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onClick(View v) {
		// 子activity里一定要继承实现此方法
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	/**
	 * OnCreate前初始化数据 初始化mContext 设置一些title等
	 */
	protected abstract void beforeCreate();

	/**
	 * OnCreate里 查找控件
	 */
	protected abstract void findViews();

	/**
	 * OnCreate里 数据初始化
	 */
	protected abstract void initDataOnCreate();

	/**
	 * OnCreate里 注册监听
	 */
	protected abstract void registerListener();

	/**
	 * OnResume里 数据初始化
	 */
	protected abstract void initDataOnResume();

	/**
	 * OnResume里 初始化视图
	 */
	protected abstract void initView();

}
