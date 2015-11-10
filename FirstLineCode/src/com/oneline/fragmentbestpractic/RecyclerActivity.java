package com.oneline.fragmentbestpractic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.oneline.adapter.GalleryAdapter;
import com.oneline.adapter.GalleryAdapter.OnItemClickListener;
import com.oneline.view.MyRecyclerView;
import com.oneline.view.MyRecyclerView.OnItemScrollChangeListener;

public class RecyclerActivity extends Activity {

	private ImageView mIvImg;
	private MyRecyclerView mRvImg;
	private List<Integer> mData;
	private GalleryAdapter mGalleryAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recycler);
		initView();
		initData();
	}

	private void initView() {
		mIvImg = (ImageView) findViewById(R.id.iv_img);
		mRvImg = (MyRecyclerView) findViewById(R.id.rv_img);

	}
	private void initData() {
		mData = new ArrayList<Integer>();
		
		Integer[] mArrayData = new Integer[] {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,R.drawable.h,R.drawable.i,};
		mData.addAll(Arrays.asList(mArrayData));
		
		setAdapter();
	}

	private void setAdapter() {
		LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
		mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
		mRvImg.setLayoutManager(mLinearLayoutManager);
		mGalleryAdapter = new GalleryAdapter(this, mData);
		mRvImg.setAdapter(mGalleryAdapter);
		
		mRvImg.setOnItemScrollChangeListener(new OnItemScrollChangeListener() {
			public void onChange(View view, int position) {
				mIvImg.setImageResource(mData.get(position));
			}
		});
		mGalleryAdapter.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(View view, int position) {
				mIvImg.setImageResource(mData.get(position));
			}
		});
	}

}
