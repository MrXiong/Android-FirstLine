package com.oneline.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.oneline.fragmentbestpractic.LoginActivity;
import com.oneline.utils.ActivityController;

public class ForceOfflineReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(final Context context, Intent intent) {
		ActivityController.finishAll(); // 销毁所有活动
		intent = new Intent(context, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent); // 重新启动LoginActivity
	}
}