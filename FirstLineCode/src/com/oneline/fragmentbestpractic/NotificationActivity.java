package com.oneline.fragmentbestpractic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.oneline.adapter.SMSAdapter;
import com.oneline.base.BaseActivity;
import com.oneline.bean.SMS;

public class NotificationActivity extends BaseActivity implements OnClickListener {

	
	private NotificationManager manager;
	/**
	 * 通知id
	 */
	private int notification_ID;
	private RecyclerView mRvSms;
	private Button mBtnSendNotice;
	private LinearLayoutManager mLinearLayoutManager;
	private List<SMS> mPhoneBookList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		initView();
		
	}

	private void initView() {
		mBtnSendNotice = (Button) findViewById(R.id.btn_send_notice);
		mRvSms = (RecyclerView) findViewById(R.id.rv_sms);
		mBtnSendNotice.setOnClickListener(this);
		
		mLinearLayoutManager = new LinearLayoutManager(this);
		mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRvSms.setLayoutManager(mLinearLayoutManager);
		
		mPhoneBookList = new ArrayList<SMS>();
		
		SMSAdapter adapter = new SMSAdapter(this, getSmsInPhone());
		mRvSms.setAdapter(adapter);
	}
	private void sendNotification() {
		Intent intent = new Intent(this,MainActivity.class);
		PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);
		Notification.Builder builder = new Notification.Builder(this);
		// 设置图标
		builder.setSmallIcon(R.drawable.ic_launcher);
		// 手机状态栏的提示；
		builder.setTicker("hello");
		// 设置时间
		builder.setWhen(System.currentTimeMillis());
		// 设置标题
		builder.setContentTitle("通知栏通知");
		// 设置通知内容
		builder.setContentText("我来自NotificationDemo");
		// 点击后的意图
		builder.setContentIntent(pintent);
		// 设置提示声音
//		builder.setDefaults(Notification.DEFAULT_SOUND);
		// 设置指示灯
//		builder.setDefaults(Notification.DEFAULT_LIGHTS);
		// 设置震动
//		builder.setDefaults(Notification.DEFAULT_VIBRATE);
		// 设置震动
		builder.setDefaults(Notification.DEFAULT_ALL);
		// 4.1以上
		Notification notification = builder.build();
		//builder.getNotification();
		manager.notify(notification_ID, notification);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_send_notice:
			sendNotification();
			break;

		default:
			break;
		}
		 
	}
	 /**
     * 获取手机短信内容
     * @return
     */
	public List<SMS> getSmsInPhone() {
		final String SMS_URI_ALL = "content://sms/"; 		//所有短信
		final String SMS_URI_INBOX = "content://sms/inbox";	//收信箱
		final String SMS_URI_SEND = "content://sms/sent";	//发信箱
		final String SMS_URI_DRAFT = "content://sms/draft";	//草稿箱
		StringBuilder smsBuilder = new StringBuilder();
		try {
			ContentResolver cr = getContentResolver();
			String[] projection = new String[] { "_id", "address", "person",
					"body", "date", "type" };
			Uri uri = Uri.parse(SMS_URI_ALL);
			Cursor cur = cr.query(uri, projection, null, null, "date desc");
			if (cur.moveToFirst()) {
				String name;
				String phoneNumber;
				String smsbody;
				String date;
				String type;
				int nameColumn = cur.getColumnIndex("person");//姓名
				int phoneNumberColumn = cur.getColumnIndex("address");//手机号
				int smsbodyColumn = cur.getColumnIndex("body");//短信内容
				int dateColumn = cur.getColumnIndex("date");//日期
				int typeColumn = cur.getColumnIndex("type");//收发类型 1表示接受 2表示发送
				do {
					name = cur.getString(nameColumn);
					phoneNumber = cur.getString(phoneNumberColumn);
					smsbody = cur.getString(smsbodyColumn);
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					Date d = new Date(Long.parseLong(cur.getString(dateColumn)));
					date = dateFormat.format(d);
					int typeId = cur.getInt(typeColumn);
					if (typeId == 1) {
						type = "接收";
					} else if (typeId == 2) {
						type = "发送";
					} else {
						type = "";
					}
					SMS sms = new SMS();
					sms.setPhoneName(name);
					sms.setPhoneNumber(phoneNumber);
					sms.setSmsBody(smsbody);
					sms.setDate(date);
					sms.setType(type);
					mPhoneBookList.add(sms);
					if (smsbody == null)
						smsbody = "";
				} while (cur.moveToNext());
			} else {
				smsBuilder.append("没有记录!");
			}
			smsBuilder.append("获取彩信完成!");
		} catch (SQLiteException ex) {
			Log.d("SQLiteException in getSmsInPhone", ex.getMessage());
		}
		return mPhoneBookList;
	}
}
