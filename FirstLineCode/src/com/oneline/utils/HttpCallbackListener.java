package com.oneline.utils;

public interface HttpCallbackListener {

	void onFinish(String response);
	void onError(Exception e);
}
