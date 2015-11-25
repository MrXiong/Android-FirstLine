package com.oneline.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.oneline.fragmentbestpractic.R;

public class TitleView extends FrameLayout implements OnClickListener{

	private Button mTitleLeft;
	private TextView mTitleText;

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this, true);
		mTitleLeft = (Button) findViewById(R.id.title_left);
		mTitleText = (TextView) findViewById(R.id.title_text);
		mTitleLeft.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.title_left:
			((Activity) getContext()).finish();
			break;

		default:
			break;
		}

	}
	
	public void setTitleText(String titleText){
		mTitleText.setText(titleText);
	}
	public void setTitleLeftText(String titleLeftText){
		mTitleLeft.setText(titleLeftText);
	}
	public void setLeftButtonListener(OnClickListener l){
		mTitleLeft.setOnClickListener(l);
	}

}
