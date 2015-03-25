package com.example.simpleandroidhttp.utils;



import com.example.simpleandroidhttp.SimpleAndroidHttpApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

/**
 * 提示对话框工具
 * 
 * @author Jelly.wen
 * 
 */
@SuppressLint("ShowToast")
public class ToastUtils {

	private static final String TAG = ToastUtils.class.getName();

	/**
	 * 弹出提示框
	 * 
	 * @param context
	 * @param message
	 */
	public static void showToast(final String message) {
		ZozoLog.i(TAG,"ToastUtil    RecuritWorkerApplication.getInstance().handler--->"+SimpleAndroidHttpApplication.getInstance().getHandler());
		SimpleAndroidHttpApplication.getInstance().getHandler().post(new Runnable() {

			@Override
			public void run() {
				ZozoLog.i(TAG,"ToastUtil    RecuritWorkerApplication.getInstance()--->"+(Context)SimpleAndroidHttpApplication.getInstance());
				ZozoLog.i(TAG,"ToastUtil    message--->"+message);
				Toast.makeText((Context)SimpleAndroidHttpApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
			}
		});
	}

	// 备用
	// public static void showToast(final IMyActivity context, final String
	// message) {
	// context.post(new Runnable() {
	// @Override
	// public void run() {
	// if (TextUtils.isEmpty(message)) {
	// return;
	// }
	// Toast.makeText(context.getThisActivity(), message,
	// Toast.LENGTH_SHORT).show();
	// }
	// });
	//
	// }
	/**
	 * 投诉用 toast类型的dialog
	 * 
	 * @param context
	 * @param message
	 */
	public static void showTipsToast(final String msgInfo, final String msgTips) {
		// LockScreenApplication.getInstance().getHandler().post(new Runnable()
		// {
		//
		// @Override
		// public void run() {
		// LayoutInflater inflater = (LayoutInflater)
		// LockScreenApplication.getInstance().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// View layout = inflater.inflate(R.layout.dialog_break_promise, null);
		// TextView msgInfoTv = (TextView) layout.findViewById(R.id.msg_info);
		// TextView msgTipTv = (TextView) layout.findViewById(R.id.msg_tip);
		// msgInfoTv.setText(msgInfo);
		// msgTipTv.setText(msgTips);
		// layout.findViewById(R.id.ok).setVisibility(View.GONE);
		// layout.findViewById(R.id.cancel).setVisibility(View.GONE);
		// Toast toast = new
		// Toast(LockScreenApplication.getInstance().getApplicationContext());
		// toast.setGravity(Gravity.CENTER, 0, 0);
		// toast.setDuration(Toast.LENGTH_SHORT);
		// toast.setView(layout);
		// toast.show();
		// }
		// });
	}
}
