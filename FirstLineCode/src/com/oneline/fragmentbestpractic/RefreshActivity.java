package com.oneline.fragmentbestpractic;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

public class RefreshActivity extends Activity {

	private SwipeRefreshLayout mSwipeRefreshOnline;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_refresh);
		initView();
	}

	private void initView() {
		mSwipeRefreshOnline = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_online);
	}

}
