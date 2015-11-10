package com.oneline.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

public class ActivityController {

	public static List<Activity> listActivity = new ArrayList<Activity>();

	public static void addActivity(Activity activity) {
		if(!listActivity.contains(activity))
		listActivity.add(activity);
	}

	public static void removeActivity(Activity activity) {
		listActivity.remove(activity);
	}

	public static void finishAll() {
		for (Activity activity : listActivity) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

}
