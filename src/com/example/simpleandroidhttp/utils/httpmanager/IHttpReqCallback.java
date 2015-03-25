/*
 * Copyright (C) 2007 The Android  Source Project
 *
 * Licensed under the RichenInfo License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.richeninfo.com/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.simpleandroidhttp.utils.httpmanager;

/**
 * @Author: wenJun
 * @Title:
 * @Description:
 * @Date: 2013-5-2
 * @Version: 1.1.0
 */
public interface IHttpReqCallback {

	/**
	 * @Author wenJun
	 * @Description 服务返回数据异常，或构造网络参数出错
	 * @Date 2013-6-6
	 */
	void onHttpReqDataError(int what);

	/**
	 * @Author wenJun
	 * @Description 网络连接异常时
	 * @Date 2013-5-2
	 */
	void onHttpReqConnFail(int what);

	/**
	 * @Author wenJun
	 * @Description 网络连接超时
	 * @Date 2013-5-2
	 */
	void onHttpReqTimeout(int what);

	/**
	 * @Author wenJun
	 * @Description 服务器拒绝响应
	 * @Date 2013-5-2
	 */
	void onHttpReqServerRefused(int what);

	/**
	 * @Author wenJun
	 * @Description 服务器返回 结果
	 * @Date 2013-5-2
	 * @param result
	 * @throws Exception
	 */
	void onHttpReqConnSuccess(String result, int what);

	/**
	 * 检查网络
	 * 
	 * @param what
	 * @return
	 */
	boolean isNetworkAvailable(int what);

	/**
	 * @Author wenJun
	 * @Description 请求开始
	 * @Date 2013-11-14
	 * @param what
	 */
	void onHttpReqStart(int what);

	/**
	 * @Author wenJun
	 * @Description 请求结束
	 * @Date 2013-11-14
	 * @param what
	 */
	void onHttpReqEnd(int what);

	/**
	 * @Author wenJun
	 * @Description 请求取消
	 * @Date 2013-11-14
	 * @param what
	 */
	void onHttpReqCancelled(int what);
}
