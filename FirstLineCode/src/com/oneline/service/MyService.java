
  
package com.oneline.service;  

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{

	private static final String TAG = MyService.class.getSimpleName();
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder; 
	}
	//创建
	public void onCreate() {
		super.onCreate(); 
		Log.d(TAG, "startDownload executed");
	}
	
	//启动
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "startDownload executed");
		return super.onStartCommand(intent, flags, startId); 
	}
	
	//销毁
	public void onDestroy() {
		super.onDestroy(); 
		Log.d(TAG, "startDownload executed");
	}
	
	private DownLoadBinder mBinder = new DownLoadBinder();

	public class DownLoadBinder extends Binder {
		

		public void startDownload() {
			Log.d(TAG, "startDownload executed");
		}

		public int getProgress() {
			Log.d(TAG, "getProgress executed");
			return 0;
		}

	}

}
  