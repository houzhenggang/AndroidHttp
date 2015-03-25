package com.example.simpleandroidhttp.utils.httpmanager;

import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;

import com.example.simpleandroidhttp.SimpleAndroidHttpApplication;
import com.example.simpleandroidhttp.utils.NetworkUtil;
import com.example.simpleandroidhttp.utils.TextUtil;
import com.example.simpleandroidhttp.utils.ToastUtils;
import com.example.simpleandroidhttp.utils.ZozoLog;
import com.example.simpleandroidhttp.utils.httpmanager.HttpRequest.METHOD;


import android.os.AsyncTask;



public class HttpAsyncTask extends AsyncTask<Void, Void, String> {

	private static final String TAG = HttpAsyncTask.class.getSimpleName();
	private static final String SERVER_REFUSED = "server refused";

	/**
	 * 新建不同长度的线程池
	 */
	// private static ExecutorService SINGLE_TASK_EXECUTOR;
	// private static ExecutorService LIMITED_TASK_EXECUTOR;
	// private static ExecutorService FULL_TASK_EXECUTOR;
	//
	// static {
	// SINGLE_TASK_EXECUTOR = (ExecutorService)
	// Executors.newSingleThreadExecutor();
	// LIMITED_TASK_EXECUTOR = (ExecutorService)
	// Executors.newFixedThreadPool(8);
	// FULL_TASK_EXECUTOR = (ExecutorService) Executors.newCachedThreadPool();
	// };

	/**
	 * 状态
	 */
	private int mState = -1;

	private final static int STATE_CONN_ERROR = 0x0001;
	private final static int STATE_CONN_TIMEOUT = 0x0002;
	private final static int STATE_SERVER_REFUSED = 0x0003;
	private final static int STATE_CONN_SUCCESSED = 0x0004;
	private final static int STATE_DATA_ERROR = 0x0005;

	private HttpRequest httpRequest;
	/**
	 * 回调函数
	 */
	private IHttpReqCallback callback;
	private int identify;
	/**
	 * 本次请求方法
	 */
	private METHOD mMethod;

	public HttpAsyncTask(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
		this.callback = httpRequest.getCallback();
		this.identify = httpRequest.getIdentify();
		this.mMethod = httpRequest.getmMethod();
		// 设置线程数量
		// this.executeOnExecutor(FULL_TASK_EXECUTOR);
	}

	/**
	 * @Author wenJun
	 * @Description 当前task是否已经执行完成
	 * @Date 2013-11-14
	 * @return
	 */
	public boolean isTaskComplete() {
		return mState == STATE_CONN_SUCCESSED;
	}

	@Override
	protected void onPreExecute() {
		ZozoLog.i(TAG, "onPreExecute: ");
		super.onPreExecute();
		if (!callback.isNetworkAvailable(identify)) {
			ZozoLog.i(TAG, "网络异常");
			onCancelled();
		}
		callback.onHttpReqStart(identify);
	}

	@Override
	protected String doInBackground(Void... params) {
		ZozoLog.i(TAG, "doInBackground");
		String result = "";
		try {
			if (NetworkUtil.isNetworkAvailable(SimpleAndroidHttpApplication.getInstance().getApplicationContext()) == false) {
				ToastUtils.showToast("当前网络不可用,请检查您的网络设置");
				throw new ConnectException();
			}
			String url = httpRequest.getUrl();
			ZozoLog.i(TAG," doInBackground  请求前的cookies---->"+SimpleAndroidHttpApplication.cookieStore.getCookies().toString());
			switch (mMethod) {
			case GET:
				result = HttpUtils.getRequest(url, httpRequest.getReqParams(), httpRequest.getCookies());
				break;
			case POST:
				result = HttpUtils.postRequest(url, httpRequest.getReqParams(), httpRequest.getCookies());
				break;
			case UPLOADFILE:
				result = HttpUtils.doPost(url, httpRequest.getReqParams(), httpRequest.getFile());
				break;
			case DOWNLOADFILE:
				result = HttpUtils.doDownloadFile(url, httpRequest.getDownloadFileSavePath(), httpRequest.getDownloadFileName());
				break;

			default:
				break;
			}
			ZozoLog.i(TAG, "doInBackground :result = " + result);
			if (TextUtil.isEmpty(result)) {
				result = "";
				throw new ConnectException();
			}
			mState = STATE_CONN_SUCCESSED;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			mState = STATE_DATA_ERROR;
		} catch (ConnectException e) {
			e.printStackTrace();
			mState = STATE_CONN_ERROR;
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
			mState = STATE_CONN_TIMEOUT;
		} catch (IOException e) {
			e.printStackTrace();
			if (SERVER_REFUSED.equals(e.getMessage())) {
				mState = STATE_SERVER_REFUSED;
			} else {
				mState = STATE_DATA_ERROR;
			}
		} catch (Exception e) {
			mState = STATE_DATA_ERROR;
		}

		if (TextUtil.isEmpty(result)) {
			result = "";
		}

		return result;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		callback.onHttpReqEnd(identify);
		ZozoLog.i(TAG," onPostExecute  请求后的cookies---->"+SimpleAndroidHttpApplication.cookieStore.getCookies().toString());
		switch (mState) {
		case STATE_CONN_SUCCESSED:
			callback.onHttpReqConnSuccess(result, identify);
			break;
		case STATE_CONN_ERROR:
			callback.onHttpReqConnFail(identify);
			break;
		case STATE_SERVER_REFUSED:
			callback.onHttpReqServerRefused(identify);
			break;
		case STATE_CONN_TIMEOUT:
			callback.onHttpReqTimeout(identify);
			break;
		case STATE_DATA_ERROR:
			callback.onHttpReqDataError(identify);
			break;
		}
	}

	@Override
	protected void onCancelled() {

		callback.onHttpReqCancelled(identify);
	}
}
