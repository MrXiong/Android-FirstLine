
  
package com.oneline.adapter;  

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oneline.bean.PhoneBook;
import com.oneline.fragmentbestpractic.R;

public class PhoneBookAdapter extends RecyclerView.Adapter<PhoneBookAdapter.ViewHolder>{

	private List<PhoneBook> mPhoneBookList;
	private LayoutInflater mLayoutInflater;
	public PhoneBookAdapter(Context context ,List<PhoneBook> phoneBookList){
		this.mPhoneBookList = phoneBookList;
		mLayoutInflater = LayoutInflater.from(context);
	}
	static class ViewHolder extends RecyclerView.ViewHolder{

		public ViewHolder(View itemView) {
			super(itemView);  
		}
		TextView tvPhoneName;
		TextView tvPhoneNum;
	}

	@Override
	public int getItemCount() {
		return mPhoneBookList.size(); 
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int i) {
		final PhoneBook phoneBook = mPhoneBookList.get(i);
		holder.tvPhoneName.setText("姓名："+phoneBook.getTelePhoneName());
		holder.tvPhoneNum.setText("电话号码："+phoneBook.getTelePhoneNumber());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		View view = mLayoutInflater.inflate(R.layout.phone_item, parent, false);
		ViewHolder holder = new ViewHolder(view);
		holder.tvPhoneName = (TextView) view.findViewById(R.id.tv_phone_name);
		holder.tvPhoneNum = (TextView) view.findViewById(R.id.tv_phone_num);
		return holder; 
	}
}
  