
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
package com.example.simpleandroidhttp.utils;

import java.util.HashMap;
import java.util.Iterator;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * @ClassName: RIHandlerManager 
 * @Description: 为每一个HandlerInterface类生成唯一的handler
 * @author: dean
 * @date:2013-1-16 下午5:41:44 
 */
public class ProgressDialogManager{
	
	private static final String TAG = "ProgressDialogManager";
	  private static  HashMap<Context, ProgressDialog> progressDialogContainer = new HashMap<Context, ProgressDialog>();
	  
	  private static ProgressDialogManager progressDialogManager  = new ProgressDialogManager();
	  
	  private ProgressDialogManager(){}
	  
   /** 
    * @Description: 获取RIHandlerManager单例
    * @return RIHandlerManager   
    */
    public static ProgressDialogManager getProgressDialogManager() {
    		return progressDialogManager;
      }
      
   /** 
    * @Description: 根据一个类获取一个handler，如果map中没有则创建并加入map中。 
    * @param hInterface
    * @return RIHandler   
    */
    public ProgressDialog getProgressDialog(Context mContext){
    	ProgressDialog mProgressDialog = progressDialogContainer.get(mContext);
    	  if(mProgressDialog == null){
    		  mProgressDialog = new ProgressDialog(mContext);
    		  progressDialogContainer.put(mContext, mProgressDialog);
    	  }
    	  return mProgressDialog;
      }
      
   /** 
    * @Description: 注销handler 
    * @param  hInterface 要注销的类
    * @return void   
    */
    public void removeHandler(Context mContext){
    	  if(progressDialogContainer!=null){
    		  progressDialogContainer.remove(mContext);
    	  }
      }
    
   /** 
    * @Description: 清空map并销毁manager
    * @param 
    * @return void   
    */
    public void destroyManager(){
    	  Iterator<Context> it = progressDialogContainer.keySet().iterator();
    	  while(it.hasNext()){
    		  Context mContext = it.next();
    		  ProgressDialog mProgressDialog = progressDialogContainer.get(mContext);
    		  closeLoadingDianlog(mProgressDialog);
    	  }
    	  progressDialogContainer.clear();
    	  progressDialogContainer = null;
    	  progressDialogManager = null;
      }
    
	public void showLoadingDialog(ProgressDialog mProgressDialog, String loadingTitle, String loadingMessage, boolean cancelable) {
		ZozoLog.i(TAG, "====showLoadingDialog====");
		if (!mProgressDialog.isShowing()) {
			mProgressDialog.setTitle(loadingTitle);
			mProgressDialog.setMessage(loadingMessage);
			mProgressDialog.setCancelable(cancelable);
			mProgressDialog.show();
		}		
	}

	public void closeLoadingDianlog(ProgressDialog mProgressDialog) {
		ZozoLog.i(TAG, "====closeDialog====");
		if (mProgressDialog != null) {
			try {
				mProgressDialog.dismiss();
			} catch (Exception e) {
				mProgressDialog = null;
				ZozoLog.e(TAG, "closeLoadingDianlog : catch e = "+e);
			}
			mProgressDialog = null;
		}
	}
}
