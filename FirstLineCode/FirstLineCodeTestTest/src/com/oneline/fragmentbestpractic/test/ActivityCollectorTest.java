package com.oneline.fragmentbestpractic.test;

import android.test.AndroidTestCase;

import com.oneline.fragmentbestpractic.LoginActivity;
import com.oneline.utils.ActivityController;

public class ActivityCollectorTest extends AndroidTestCase {
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testAddActivity() {
		assertEquals(0, ActivityController.listActivity.size());
		LoginActivity loginActivity = new LoginActivity();
		ActivityController.addActivity(loginActivity);
		assertEquals(1, ActivityController.listActivity.size());
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
