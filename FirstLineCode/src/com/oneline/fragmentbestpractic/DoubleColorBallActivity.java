package com.oneline.fragmentbestpractic;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.oneline.base.BaseActivity;
import com.oneline.bean.CountBall;
import com.oneline.bean.SysBall;
import com.oneline.bean.UserBall;
import com.oneline.weather.util.DoubleColorBall;

public class DoubleColorBallActivity extends BaseActivity implements OnClickListener {

	private Button mBtnStartSye;
	private Button mBtnStartUser;
	private Button mBtnWin;
	private TextView mTvRedBall1;
	private TextView mTvRedBall2;
	private TextView mTvRedBall3;
	private TextView mTvRedBall4;
	private TextView mTvRedBall5;
	private TextView mTvRedBall6;
	private TextView mTvBlueBall;
	private TextView mTvUserRedBall1;
	private TextView mTvUserRedBall2;
	private TextView mTvUserRedBall3;
	private TextView mTvUserRedBall4;
	private TextView mTvUserRedBall5;
	private TextView mTvUserRedBall6;
	private TextView mTvUserBlueBall;
	private DoubleColorBall mDoubleColorBall;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_double_color_ball);
		mDoubleColorBall = new DoubleColorBall();
		initView();
	}

	private void initView() {
		mBtnStartSye = (Button) findViewById(R.id.btn_start_sys);
		mBtnStartUser = (Button) findViewById(R.id.btn_start_user);
		mBtnWin = (Button) findViewById(R.id.btn_win);
		mBtnStartSye.setOnClickListener(this);
		mBtnStartUser.setOnClickListener(this);
		mBtnWin.setOnClickListener(this);
		//系统球
		mTvRedBall1 = (TextView) findViewById(R.id.tv_redball1);
		mTvRedBall2 = (TextView) findViewById(R.id.tv_redball2);
		mTvRedBall3 = (TextView) findViewById(R.id.tv_redball3);
		mTvRedBall4 = (TextView) findViewById(R.id.tv_redball4);
		mTvRedBall5 = (TextView) findViewById(R.id.tv_redball5);
		mTvRedBall6 = (TextView) findViewById(R.id.tv_redball6);
		mTvBlueBall = (TextView) findViewById(R.id.tv_blueball);
		//用户球
		mTvUserRedBall1 = (TextView) findViewById(R.id.tv_user_redball1);
		mTvUserRedBall2 = (TextView) findViewById(R.id.tv_user_redball2);
		mTvUserRedBall3 = (TextView) findViewById(R.id.tv_user_redball3);
		mTvUserRedBall4 = (TextView) findViewById(R.id.tv_user_redball4);
		mTvUserRedBall5 = (TextView) findViewById(R.id.tv_user_redball5);
		mTvUserRedBall6 = (TextView) findViewById(R.id.tv_user_redball6);
		mTvUserBlueBall = (TextView) findViewById(R.id.tv_user_blueball);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start_sys:
			SysBall sysBall = mDoubleColorBall.addSysBall();
				mTvRedBall1.setText(sysBall.getSysRedBall()[0]+"");
				mTvRedBall2.setText(sysBall.getSysRedBall()[1]+"");
				mTvRedBall3.setText(sysBall.getSysRedBall()[2]+"");
				mTvRedBall4.setText(sysBall.getSysRedBall()[3]+"");
				mTvRedBall5.setText(sysBall.getSysRedBall()[4]+"");
				mTvRedBall6.setText(sysBall.getSysRedBall()[5]+"");
				mTvBlueBall.setText(sysBall.getSysBlueBall()+"");
			
			
			break;
		case R.id.btn_start_user:
			UserBall userBall = mDoubleColorBall.addUserBall();
			mTvUserRedBall1.setText(userBall.getUserRedBall()[0]+"");
			mTvUserRedBall2.setText(userBall.getUserRedBall()[1]+"");
			mTvUserRedBall3.setText(userBall.getUserRedBall()[2]+"");
			mTvUserRedBall4.setText(userBall.getUserRedBall()[3]+"");
			mTvUserRedBall5.setText(userBall.getUserRedBall()[4]+"");
			mTvUserRedBall6.setText(userBall.getUserRedBall()[5]+"");
			mTvUserBlueBall.setText(userBall.getUserBlueBall()+"");
			
			break;
		case R.id.btn_win:
			CountBall countBall = mDoubleColorBall.checkWinCount();
			mBtnWin.setText("中奖红球="+countBall.getWinRedBall()+"中奖蓝球"+countBall.getWinBlueBall());
			break;

		default:
			break;
		}
		
	}

}
