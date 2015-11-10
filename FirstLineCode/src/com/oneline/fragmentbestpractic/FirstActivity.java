package com.oneline.fragmentbestpractic;

import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.oneline.adapter.FirstAdapter;
import com.oneline.base.BaseActivity;
import com.oneline.bean.ActivityContent;
import com.oneline.weather.activity.ChooseAreaActivity;
import com.oneline.weather.activity.WeatherActivity;

public class FirstActivity extends BaseActivity {

	private List<ActivityContent> mList;
	private ListView mLvFrist;
	private FirstAdapter mAdapter;
	private ActivityContent mActivityContent;
	private String[] names = { "main", "broadcast", "login", "recycler",
			"PhoneBook", "notification", "拍照","service","SensorActivity","weather" };
	private Class<?>[] classs = { MainActivity.class, BroadCastActivity.class,
			LoginActivity.class, RecyclerActivity.class,
			PhoneBookActivity.class, NotificationActivity.class,ChoosePicActivity.class,ServiceActivity.class
			,SensorActivity.class,ChooseAreaActivity.class};

	@Override
	protected void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		initView();
		initData();
		

	}

	private void initView() {
		mLvFrist = (ListView) findViewById(R.id.lv_first);
		
	}

	private void initData() {
		mList = new ArrayList<ActivityContent>();
		for (int i = 0; i < names.length; i++) {
			mActivityContent = new ActivityContent();
			mActivityContent.setContent(names[i]);
			mActivityContent.setCls(classs[i]);
			mList.add(mActivityContent);
		}
		setAdapter();

	}

	private void setAdapter() {
		mAdapter = new FirstAdapter(mList);
		mLvFrist.setAdapter(mAdapter);

		mLvFrist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				final ActivityContent activityContent = mAdapter.getItem(position);
				 sendActivity(FirstActivity.this, activityContent.getCls());
			}
		});
	}

}
