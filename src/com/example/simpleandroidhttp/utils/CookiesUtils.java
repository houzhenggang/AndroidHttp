package com.example.simpleandroidhttp.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.w3c.dom.Text;

import android.text.TextUtils;

import com.example.simpleandroidhttp.SimpleAndroidHttpApplication;

public class CookiesUtils {

	private static final String TAG = "CookiesUtils";

	public static boolean saveCookies() {

		List<Cookie> cookieList = SimpleAndroidHttpApplication.cookieStore.getCookies();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cookieList.size(); i++) {
			Cookie cookie = cookieList.get(i);
			String cookieName = cookie.getName();
			String cookieValue = cookie.getValue();
			String cookieDomain = cookie.getDomain();
			if(!TextUtils.isEmpty(cookieName)){
				SharedUtilForJS.getInstance(SimpleAndroidHttpApplication.getInstance()).setSharedStringKey(cookieName, cookieValue);
			}
			SharedUtils.getInstance(SimpleAndroidHttpApplication.getInstance()).setSharedStringKey("cookieDomain", cookieDomain);
			if (!TextUtils.isEmpty(cookieName) && !TextUtils.isEmpty(cookieValue) && !TextUtils.isEmpty(cookieDomain)) {
				sb.append(cookieName + "=");
				sb.append(cookieValue + ";");
			}
		}
		String strCookie = sb.toString();
		SharedUtils.getInstance(SimpleAndroidHttpApplication.getInstance()).setSharedStringKey("cookie", strCookie);
		SharedUtils.getInstance(SimpleAndroidHttpApplication.getInstance()).setSharedStringKey("cookieList", cookieList.toString());
		//
		return true;
	}

	public static CookieStore getCookies() {

		String strCookie = SharedUtils.getInstance(SimpleAndroidHttpApplication.getInstance()).getSharedStringKey("cookie");
		
		if (strCookie != null && !TextUtils.isEmpty(strCookie)) {
			String[] cookies = strCookie.split(";");
			ZozoLog.i(TAG,"CookiesUtils      cookies--->"+Arrays.toString(cookies));
			for (int i = 0; i < cookies.length; i++) {
				String[] arr = cookies[i].split("=");
				BasicClientCookie cookie = new BasicClientCookie(arr[0], arr[1]);
				cookie.setDomain(SharedUtils.getInstance(SimpleAndroidHttpApplication.getInstance()).getSharedStringKey("cookieDomain"));
				SimpleAndroidHttpApplication.cookieStore.addCookie(cookie);
			}
			return SimpleAndroidHttpApplication.cookieStore;
		} else {
			return SimpleAndroidHttpApplication.cookieStore;
		}
	}

	public static String getCookiesForString() {
		// TODO Auto-generated method stub
		return  SharedUtils.getInstance(SimpleAndroidHttpApplication.getInstance()).getSharedStringKey("cookie");
	}

}
