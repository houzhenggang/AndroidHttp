package com.example.simpleandroidhttp.utils.httpmanager;

import java.io.File;
import java.util.Map;

import org.apache.http.client.CookieStore;

import com.example.simpleandroidhttp.Constants;
import com.example.simpleandroidhttp.utils.ToastUtils;



/**
 * @author Jelly.wen 网络请求对象化
 */
public class HttpRequest {

	private static final String TAG = HttpRequest.class.getSimpleName();

	/**
	 * 枚举类型来标识请求方法
	 */
	public static enum METHOD {
		POST, // post请求
		GET, // get请求
		DOWNLOADFILE, // 下载文件
		UPLOADFILE // 上传文件
	};

	/**
	 * 本次请求方法
	 */
	private METHOD mMethod;

	/**
	 * 请求网址
	 */
	private String url = Constants.URL;

	/**
	 * 请求参数
	 */
	private Map<String, String> ReqParams;

	/**
	 * 编码方式
	 */
	private String encoding = "UTF-8";

	/**
	 * 回调函数
	 */
	private IHttpReqCallback callback = null;

	/**
	 * 请求id
	 */
	private int identify = 0;

	/**
	 * 要上传的文件
	 */
	private File file;

	/**
	 * 下载文件存放路径
	 */
	private String downloadFileSavePath;

	/**
	 * 下载文件命名
	 */
	private String downloadFileName;

	/**
	 * cookies
	 */
	private CookieStore cookies;

	public HttpRequest(METHOD method) {
		mMethod = method;

	}

	public String getDownloadFileSavePath() {
		return downloadFileSavePath;
	}

	public HttpRequest setDownloadFileSavePath(String downloadFileSavePath) {
		this.downloadFileSavePath = downloadFileSavePath;
		return this;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public HttpRequest setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public HttpRequest setUrl(String url) {
		this.url = url;
		return this;
	}

	public Map<String, String> getReqParams() {
		return ReqParams;
	}

	public HttpRequest setReqParams(Map<String, String> reqParams) {
		ReqParams = reqParams;
		return this;
	}

	public String getEncoding() {
		return encoding;
	}

	public HttpRequest setEncoding(String encoding) {
		this.encoding = encoding;
		return this;
	}

	public IHttpReqCallback getCallback() {
		return callback;
	}

	public HttpRequest setCallback(IHttpReqCallback callback) {
		this.callback = callback;
		return this;
	}

	public int getIdentify() {
		return identify;
	}

	public HttpRequest setIdentify(int identify) {
		this.identify = identify;
		return this;
	}

	public File getFile() {
		return file;
	}

	public HttpRequest setFile(File file) {
		this.file = file;
		return this;
	}

	public CookieStore getCookies() {
		return cookies;
	}

	public HttpRequest setCookies(CookieStore cookies) {
		this.cookies = cookies;
		return this;
	}

	public METHOD getmMethod() {
		return mMethod;
	}

	/**
	 * 初始化赋值 此方法可不用
	 * 
	 * @param mMethod
	 */
	public HttpRequest setmMethod(METHOD mMethod) {
		this.mMethod = mMethod;
		return this;
	}

	public void start() {
		// 检查请求合法性
		if (callback == null) {
			ToastUtils.showToast("缺少 callback");
			return;
		}
		HttpAsyncTask httpTask = new HttpAsyncTask(this);
		httpTask.execute();
	}

}
