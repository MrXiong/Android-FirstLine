package com.oneline.fragmentbestpractic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oneline.base.BaseActivity;

public class SensorActivity extends BaseActivity {

	
	private SensorManager mSensorManager;
	private TextView mTvSensorValue;
	
	private Sensor mSensorTypeLight;
	private Sensor mSensorAccelerometer;
	private Sensor mSensorOrientation;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor);
		initView();
		initSensor();
	}

	private void initView() {
		mTvSensorValue = (TextView) findViewById(R.id.tv_sensor_value);
		mIvBgNeedle = (ImageView) findViewById(R.id.iv_bg_needle);
		
	}

	private void initSensor() {
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorTypeLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);//光传感器
		mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//加速度传感器
		mSensorManager.registerListener(sensorEventListener,mSensorTypeLight, SensorManager.SENSOR_DELAY_NORMAL);
		mSensorManager.registerListener(sensorEventListenerAccelerometer,mSensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		//指南针
		Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		Sensor magneticSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		
		mSensorManager.registerListener(listener, accelerometerSensor,SensorManager.SENSOR_DELAY_GAME);
		mSensorManager.registerListener(listener, magneticSensor,SensorManager.SENSOR_DELAY_GAME);// 方向
		
		
		
	}
	
	SensorEventListener sensorEventListener = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent event) {
			float value = event.values[0];
			mTvSensorValue.setText(value+"");
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
	};
	
	float[] accelerometerValues = new float[3];
	float[] magneticValues = new float[3];
	private float lastRotateDegree;
	SensorEventListener listener = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent event) {
			if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				accelerometerValues = event.values.clone();
			} else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				// 注意赋值时要调用clone()方法
				magneticValues = event.values.clone();
				}
			float[] R = new float[9];
			float[] values = new float[3];
			SensorManager.getRotationMatrix(R, null, accelerometerValues,
			magneticValues);
			SensorManager.getOrientation(R, values);
			Log.d("MainActivity", "value[0] is " + Math.toDegrees(values[0]));
		//  将计算出的旋转角度取反，用于旋转指南针背景图
			float rotateDegree = -(float) Math.toDegrees(values[0]);
			if (Math.abs(rotateDegree - lastRotateDegree) > 1) {
			RotateAnimation animation = new RotateAnimation
			(lastRotateDegree, rotateDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.
			RELATIVE_TO_SELF, 0.5f);
			animation.setFillAfter(true);
			mIvBgNeedle.startAnimation(animation);
			lastRotateDegree = rotateDegree;
			}
			
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
	};
	SensorEventListener sensorEventListenerAccelerometer = new SensorEventListener() {
		
		public void onSensorChanged(SensorEvent event) {
			// 加速度可能会是负值，所以要取它们的绝对值
			float xValue = Math.abs(event.values[0]);
			float yValue = Math.abs(event.values[1]);
			float zValue = Math.abs(event.values[2]);
			if (xValue > 15 || yValue > 15 || zValue > 15) {
			// 认为用户摇动了手机，触发摇一摇逻辑
			Toast.makeText(SensorActivity.this, "摇一摇",
			Toast.LENGTH_SHORT).show();
			}
		}
		
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			
		}
	};
	private ImageView mIvBgNeedle;
	
	
	
	
	protected void onDestroy() {
		super.onDestroy();
		mSensorManager.unregisterListener(sensorEventListener);
		mSensorManager.unregisterListener(sensorEventListenerAccelerometer);
		mSensorManager.unregisterListener(listener);
		
	};
	
}
