package com.oneline.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

	public static final String PREF_NAME = "com.oneline.cache";
	public static final String PREF_NAME_LOGIN_PASSWORD = PREF_NAME+"login.password";

	private static SharedPreferencesUtils mInstance;
	private SharedPreferences mSharedPreferences;
	private Context mContext;

	private SharedPreferencesUtils(Context context) {
		mSharedPreferences = context.getSharedPreferences(PREF_NAME,
				Context.MODE_PRIVATE);
		mContext = context.getApplicationContext();
	}

	public static SharedPreferencesUtils getInstance(Context context) {
		if (null == mInstance) {
			synchronized (SharedPreferencesUtils.class) {
				mInstance = new SharedPreferencesUtils(context);
			}
		}
		return mInstance;
	}
	
	public void savePassword(String password) {
		mSharedPreferences.edit().putString(PREF_NAME_LOGIN_PASSWORD, password)
				.commit();
	}
	public void clearPassword(String password) {
		mSharedPreferences.edit().putString(PREF_NAME_LOGIN_PASSWORD, password)
		.commit();
	}
	public String getPassword() {
		return mSharedPreferences.getString(PREF_NAME_LOGIN_PASSWORD, null);
	}

}
