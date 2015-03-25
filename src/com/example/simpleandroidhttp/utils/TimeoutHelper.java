package com.example.simpleandroidhttp.utils;

import android.content.Context;
import android.os.Handler;

public class TimeoutHelper implements Runnable {

	private final String TAG = "TimeOutHelper";

	public final static int TIME_OUT_MSG = 51;

	private static int totalTime = 30;

	private int count = totalTime;

	private Handler handler;

	private static boolean enable = false;

	private boolean isStop = true;

	private Context mContext;

	public void setValue(int second) {
		totalTime = second;
		count = totalTime;
	}

	public void reset() {
		count = totalTime;
	}

	public void resume() {
		enable = true;
		count = totalTime;
	}

	public void pause() {
		enable = false;
	}

	public void stop() {
		isStop = true;
	}

	public TimeoutHelper(Handler handler, Context mContext) {

		isStop = true;
		enable = true;
		this.handler = handler;
		this.mContext = mContext;
	}

	public void start() {
		if (isStop) {
			isStop = false;
			new Thread(this).start();
		}
	}

	private void decrease() {
		count--;
	}

	@Override
	public void run() {
		while (!isStop) {
			try {
				Thread.currentThread();
				Thread.sleep(1000);
				ZozoLog.d(TAG, "count = " + count);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (enable) {
				if (count == 0) {
					handler.sendEmptyMessage(TIME_OUT_MSG);
				}
				decrease();
			}
		}
	}

}
