package com.truly.ic.SalePlatform;

import android.app.Application;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

public class SalePlatformApplication extends Application {

	private static final String TAG = "JPush";

	@Override
	public void onCreate() {
		
		Log.d(TAG, "[SalePlatformApplication] onCrate");
		super.onCreate();
		
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		//晚上10：30点到第二天早上8：30点为静音时段
		JPushInterface.setSilenceTime(getApplicationContext(), 22, 30, 8, 30);
	}
}
