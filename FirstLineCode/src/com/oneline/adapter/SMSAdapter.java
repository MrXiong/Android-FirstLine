
  
package com.oneline.adapter;  

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oneline.bean.SMS;
import com.oneline.fragmentbestpractic.R;

public class SMSAdapter extends RecyclerView.Adapter<SMSAdapter.ViewHolder>{

	private List<SMS> mSMSList;
	private LayoutInflater mLayoutInflater;
	public SMSAdapter(Context context ,List<SMS> phoneBookList){
		this.mSMSList = phoneBookList;
		mLayoutInflater = LayoutInflater.from(context);
	}
	static class ViewHolder extends RecyclerView.ViewHolder{

		public ViewHolder(View itemView) {
			super(itemView);  
		}
		TextView tvPhoneName;
		TextView tvPhoneNum;
		TextView tvBody;
		TextView tvDate;
		TextView tvType;
	}

	@Override
	public int getItemCount() {
		return mSMSList.size(); 
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int i) {
		final SMS phoneBook = mSMSList.get(i);
		
		holder.tvPhoneName.setText("姓名："+phoneBook.getPhoneName());
		holder.tvPhoneNum.setText("电话号码："+phoneBook.getPhoneNumber());
		holder.tvBody.setText("短信："+phoneBook.getSmsBody());
		holder.tvDate.setText("时间："+phoneBook.getDate());
		holder.tvType.setText("类型："+phoneBook.getType());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		View view = mLayoutInflater.inflate(R.layout.sms_item, parent, false);
		ViewHolder holder = new ViewHolder(view);
		holder.tvPhoneName = (TextView) view.findViewById(R.id.tv_phone_name);
		holder.tvPhoneNum = (TextView) view.findViewById(R.id.tv_phone_num);
		holder.tvBody = (TextView) view.findViewById(R.id.tv_body);
		holder.tvDate = (TextView) view.findViewById(R.id.tv_date);
		holder.tvType = (TextView) view.findViewById(R.id.tv_type);
		return holder; 
	}
}
  