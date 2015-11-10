package com.oneline.fragmentbestpractic;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;

import com.oneline.base.BaseActivity;
import com.oneline.broadcast.LocalReceiver;
import com.oneline.utils.FileUtils;

public class BroadCastActivity extends BaseActivity implements OnClickListener {
	private LocalBroadcastManager mLocalBroadcastManager;
	private LocalReceiver mLocalReceiver;
	private TextView mTvJpush;
	private Button mTvBroadcast;
	private Button mTvOrderBroadcast;
	private Button mTvForcedOffline_broadcast;
	private Button mTvLocalBroadcast;
	private EditText mEtSavefile;
	public static String fileName = "firstfile.txt";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_broad_cast);
		initView();
		
		mLocalBroadcastManager = LocalBroadcastManager.getInstance(getApplicationContext());
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction("mylocalBroadCast");
		mLocalReceiver = new LocalReceiver();
		mLocalBroadcastManager.registerReceiver(mLocalReceiver, intentFilter);
		
		//得到推送内容
		getJpushContent();
		
		//从文件中读取
		String loadString = FileUtils.getFile(this, fileName);
		if(!TextUtils.isEmpty(loadString)) {
			mEtSavefile.setText(loadString);
			mEtSavefile.setSelection(loadString.length());//光标位置
		}
		 
	}
	
	private void getJpushContent() {
		Intent intent = getIntent();
        if (null != intent) {
	        Bundle bundle = getIntent().getExtras();
	        if(bundle !=null) {
	        	String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
	        	String content = bundle.getString(JPushInterface.EXTRA_ALERT);
	        		 mTvJpush.append("title="+title+"content="+content);
	        }
	        	
        }
		 
	}

	private void initView() {
		mTvJpush = (TextView) findViewById(R.id.tv_jpush);
		mTvBroadcast = (Button) findViewById(R.id.tv_broadcast);
		mTvOrderBroadcast = (Button) findViewById(R.id.tv_order_broadcast);
		mTvForcedOffline_broadcast = (Button) findViewById(R.id.tv_forced_offline_broadcast);
		mTvLocalBroadcast = (Button) findViewById(R.id.tv_local_broadcast);
		mEtSavefile = (EditText) findViewById(R.id.et_savefile);
		
		mTvBroadcast.setOnClickListener(this);
		mTvOrderBroadcast.setOnClickListener(this);
		mTvForcedOffline_broadcast.setOnClickListener(this);
		mTvLocalBroadcast.setOnClickListener(this);
		
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy(); 
		mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
		//保存文件 输入流是程序得到数据，输出流是程序向外部输出数据
		String inputText = mEtSavefile.getText().toString();
		FileUtils.save(this,inputText,fileName);
	}

	@Override
	public void onClick(View v) {
		Intent in;
	switch (v.getId()) {
	case R.id.tv_broadcast:
		in = new Intent("myCustomBroadCast");
		sendBroadcast(in);
		break;
	case R.id.tv_order_broadcast:
		in = new Intent("myCustomBroadCast");
		sendOrderedBroadcast(in, null);
		break;
	case R.id.tv_forced_offline_broadcast:
		in = new Intent("myforcedOfflineBroadCast");
		sendBroadcast(in);
		break;
	case R.id.tv_local_broadcast:
		in = new Intent("mylocalBroadCast");
		mLocalBroadcastManager.sendBroadcast(in);
		break;

	default:
		break;
	}
		 
	}

}
