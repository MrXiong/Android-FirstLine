package com.oneline.fragmentbestpractic;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.oneline.base.BaseActivity;
import com.oneline.utils.HttpCallbackListener;
import com.oneline.utils.HttpUtil;
import com.oneline.utils.SharedPreferencesUtils;

public class LoginActivity extends BaseActivity {
	private EditText accountEdit;
	private EditText passwordEdit;
	private Button login;
	private CheckBox mCbRememberPass;
	private SharedPreferencesUtils mSharedPreferencesUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mSharedPreferencesUtils = SharedPreferencesUtils.getInstance(this);
		initView();
	}

	private void initView() {
		accountEdit = (EditText) findViewById(R.id.account);
		passwordEdit = (EditText) findViewById(R.id.password);
		mCbRememberPass = (CheckBox) findViewById(R.id.cb_remember_pass);
		login = (Button) findViewById(R.id.login);
		initListener();
	}

	private void initListener() {
		//判断是否存在保存密码
		String password = mSharedPreferencesUtils.getPassword();
		if(!TextUtils.isEmpty(password)) {
			passwordEdit.setText(password);
		}
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				String msgString = (String) msg.obj;
			}
		};
		login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String account = accountEdit.getText().toString();
				String password = passwordEdit.getText().toString();
				//记住密码
				if(mCbRememberPass.isChecked()) {
					mSharedPreferencesUtils.savePassword(password);
				} else {
					mSharedPreferencesUtils.clearPassword(null);
				}
				HttpUtil.sendHttpRequest("www.baidui.com", new HttpCallbackListener() {
					public void onFinish(String response) {
						Message msg = new Message();
						msg.obj = response;
						handler.sendMessage(msg);
					}
					public void onError(Exception e) {
						
					}
				});
					Intent intent = new Intent(LoginActivity.this,
							BroadCastActivity.class);
					startActivity(intent);
					finish();
			}
		});

	}
}