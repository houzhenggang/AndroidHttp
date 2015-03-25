package com.example.simpleandroidhttp.activity;

import java.io.File;
import java.util.HashMap;

import com.example.simpleandroidhttp.R;
import com.example.simpleandroidhttp.SimpleAndroidHttpApplication;
import com.example.simpleandroidhttp.utils.httpmanager.HttpRequest;
import com.example.simpleandroidhttp.utils.httpmanager.HttpRequest.METHOD;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity {
	
	private static final String TAG = MainActivity.class.getName();
	private Context mContext;
	private Button mBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
	}

	@Override
	protected void beforeCreate() {
		mContext = MainActivity.this;
		setContentView(R.layout.activity_main);
		
	}

	/**
	 * just look the method's name
	 */
	@Override
	protected void findViews() {
		// getView
		//mBtn = (Button)findViewById(R.id.btn);
	}

	/**
	 * get data before onResume 
	 */
	@Override
	protected void initDataOnCreate() {
		// this is a example  下面这些代码是一些例子
		// let's do httpRequest  让我们开始一次请求吧
		
		
		// init a request object with request mode  just like  post get uolpad download
		// 创建一个request对象   用有参构造模式 参数是确定请求类型的  比如get post 
		HttpRequest request = new HttpRequest(METHOD.POST);
//		HttpRequest request = new HttpRequest(METHOD.GET);
//		HttpRequest request = new HttpRequest(METHOD.UPLOADFILE);
//		HttpRequest request = new HttpRequest(METHOD.DOWNLOADFILE);
		
		// init request's property just like params url  and so on;
		
		
		//set params 
		//we set a  HashMap to the request Object
		// the bottom-method will change to different params with METHOD.
		//when we upload file ,this method also work  
		// it means we can handle uploadFile-Httprequest with params;  so amazing
		//设置请求参数  与请求地址
		//我们只需要设置一个HashMap<String,String>就行 
		//底层方法会根据post 还是get 自行处理这个map让其成功作为请求参数的
		//请求参数可以为空. 可以设置为null 
		request.setReqParams(new HashMap<String, String>());
		request.setUrl("");
		
		
		//set which one handle the httpreponse
		//设置谁来处理请求返回 一般来说都是当前的Activity 
		request.setCallback(MainActivity.this);
		// set cookie  
		//set encoding method
		//usually we needn't to set those property 
		//because the bottom-method  will set it  every times when  we do HttpRequest
		
		//设置编码模式与cookie   
		//一般这些在我们请求的时候 底层都会设置好
		//这里设置会改变底层设置
		request.setCookies(SimpleAndroidHttpApplication.cookieStore);
		request.setEncoding("");
		
		//this method we just look name   
		//this method will  reponse a path that you set...
		//这是从指定地址下载文件的方法
		//返回一个字符串path 就是你下载好的文件将要存储的位置的绝对路径
		request.setDownloadFileName("");
		request.setDownloadFileSavePath("");
		
		//this method will uoload you params's file
		//we use this method always in request's mode equals METHOD.UPLOAD FILE
		//这是上传文件的方法 参数是一个文件对象
		request.setFile(new File(""));
		
		// int fact 
		// we will do a lot of httpRequest in one Activity
		//this method will let we distinguish wihic httpRepose that we need handle
		//我么一般在一个activity里不止请求一次 
		//这个参数是我们判断处理哪个返回的重要标志 必须得有..
		request.setIdentify(1);
		
		//start httpRequest  
		// onHttpReqConnSuccess() this method will get the reponse 
		//这个方法表示开始请求  
		//请求返回在onHttpReqConnSuccess()方法里处理
		request.start();
		
	}

	/**
	 *  just look the method's name
	 */
	@Override
	protected void registerListener() {
		
		
	}

	/**
	 *  do some about mainUI's thing in  this method
	 */
	@Override
	protected void initDataOnResume() {
		
		
	}

	/**
	 * do onResume() 
	 */
	@Override
	protected void initView() {
		
		
	}

	/**
	 * we don't do something  in this method 
	 * because  it is entends the  BaseActivity
	 * all the thing has done in BaseActivity
	 */
	@Override
	public void onResume() {
		super.onResume();
	}

	/**
	 * Activity's life circle
	 */
	@Override
	public void onPause() {
		
		super.onPause();
	}

	/**
	 * Activity's life circle
	 */
	@Override
	public void onDestroy() {
		
		super.onDestroy();
	}

	/**
	 * we needn't do something  in this method 
	 * because  it is entends the  BaseActivity
	 * all the thing has done in BaseActivity
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	/**
	 * handle HttpRepose in this method
	 */
	@Override
	public void onHttpReqConnSuccess(String result, int what) {
		super.onHttpReqConnSuccess(result, what);
		
		// the result  is a  character	
		//maybe by XML type or JSON type  or other type
		//the params "what" is your request's identify
		//we can use switch(){} to handle every "what"
		//参数result是一个字符串 可以是任意类型的 你自己解析. 就行
		//参数what是你请求设置的identify..
		//我们可以根据这个identify用switch(){}来处理接下来的工作
		
		
		// just do your thing after check-null..
		//在检查参数result是否为空之后 就可以处理你自己的事情了..
		
		
	}

	/**
	 * test doesn't hava WIFI
	 */
	@Override
	public boolean isNetworkAvailable(int what) {
		
		return super.isNetworkAvailable(what);
	}

	
	/**
	 * Activity's life circle
	 */
	@Override
	public void onStop() {
		
		super.onStop();
	}

	/**
	 * implement OnlickListener  so easy
	 */
	@Override
	public void onClick(View v) {
		
		super.onClick(v);
	}

	/**
	 * Activity's life circle
	 */
	@Override
	protected void onRestart() {
		
		super.onRestart();
	}

	/**
	 * Activity's life circle
	 */
	@Override
	protected void onStart() {
		
		super.onStart();
	}
	
	
}
