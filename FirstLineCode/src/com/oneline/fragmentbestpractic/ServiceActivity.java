package com.oneline.fragmentbestpractic;

import java.util.List;

import org.json.JSONObject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.oneline.service.MyService;

public class ServiceActivity extends Activity implements OnClickListener {

	private static final String TAG = ServiceActivity.class.getSimpleName();
	private Button mBtnStart;
	private Button mBtnStop;
	private Button mBtnBind;
	private Button mBtnUnBind;

	private TextView mTvLocation;
	private LocationManager mLocationManager;
	private String provider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service);
		initView();
		mQueue = Volley.newRequestQueue(this);  
		Log.d(TAG, "onCreate executed");
		initLocation();
	}

	private void initLocation() {
		// 获得位置管理器
		mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// 获得位置内容提供者
		List<String> providers = mLocationManager.getProviders(true);
		if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
			provider = LocationManager.NETWORK_PROVIDER;
		} else if (providers.contains(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else {
			// 没有可用的位置提供器
			Toast.makeText(this, "No location provider to use",
					Toast.LENGTH_SHORT).show();
			return;
		}
		Location location = mLocationManager.getLastKnownLocation(provider);
		if (null != location) {
			showLocation(location);
		}

		mLocationLis = new LocationLis();
		mLocationManager
				.requestLocationUpdates(provider, 2000, 1, mLocationLis);

	}

	class LocationLis implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			showLocation(location);
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onProviderDisabled(String provider) {

		}

	}

	/*
	 * private void showLocation(Location location) { String currentPosition =
	 * "latitude is " + location.getLatitude() + "\n" + "longitude is " +
	 * location.getLongitude(); mTvLocation.setText(currentPosition); }
	 */

	private void initView() {
		mBtnStart = (Button) findViewById(R.id.btn_start);
		mBtnStop = (Button) findViewById(R.id.btn_stop);
		mBtnBind = (Button) findViewById(R.id.btn_bind);
		mBtnUnBind = (Button) findViewById(R.id.btn_unbind);
		mTvLocation = (TextView) findViewById(R.id.tv_location);
		mBtnStart.setOnClickListener(this);
		mBtnStop.setOnClickListener(this);
		mBtnBind.setOnClickListener(this);
		mBtnUnBind.setOnClickListener(this);

	}

	private ServiceConnection connection = new ServiceConnection() {

		public void onServiceDisconnected(ComponentName name) {

		}

		public void onServiceConnected(ComponentName name, IBinder service) {
			MyService.DownLoadBinder binder = (MyService.DownLoadBinder) service;
			binder.startDownload();
			binder.getProgress();
		}
	};
	private LocationLis mLocationLis;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			startService();
			break;
		case R.id.btn_stop:
			stopService();
			break;
		case R.id.btn_bind:
			Intent bindIntent = new Intent(this, MyService.class);
			bindService(bindIntent, connection, BIND_AUTO_CREATE); //
			break;
		case R.id.btn_unbind:
			unbindService(connection); //
			break;

		default:
			break;
		}

	}

	private void stopService() {
		Intent startIntent = new Intent(this, MyService.class);
		stopService(startIntent); // 停止服务
	}

	private void startService() {
		Intent startIntent = new Intent(this, MyService.class);
		startService(startIntent); // 启动服务
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mLocationManager != null) {
			// 关闭程序时把监听器移除
			mLocationManager.removeUpdates(mLocationLis);
		}
	}

	// 定位
	public static final int SHOW_LOCATION = 0;

	private void showLocation(final Location location) {
		
		// 组装反向地理编码的接口地址
		StringBuilder url = new StringBuilder();
		url.append("http://maps.googleapis.com/maps/api/geocode/json?latlng=");
		url.append(location.getLatitude()).append(",");
		url.append(location.getLongitude());
		url.append("&sensor=false");
		
		String urlString="http://open.treebear.cn/router?method=ivy.store.list.search&storeTypeId=100103&v=1.0&appKey=36248&longitude=120.09789&pageSize=15&latitude=30.266175&startNo=1&cityId=hangzhou&sign=1F4A8F4545C3F129D982D94323C41B853DBD803E";
		jsonRequest(urlString);
	}

	private void jsonRequest(String url) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,  
		        new Response.Listener<JSONObject>() {  
		            @Override  
		            public void onResponse(JSONObject response) {  
		                Log.v("TAG", response.toString());  
		                
		                
		                
		                
		            }  
		        }, new Response.ErrorListener() {  
		            @Override  
		            public void onErrorResponse(VolleyError error) {  
		                Log.e("TAG", error.getMessage(), error);  
		            }  
		        });  
		mQueue.add(jsonObjectRequest);
	}

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SHOW_LOCATION:
				String currentPosition = (String) msg.obj;
				mTvLocation.setText(currentPosition);
				break;
			default:
				break;
			}
		}
	};
	private RequestQueue mQueue;

}
