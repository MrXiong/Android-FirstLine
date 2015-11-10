package com.oneline.adapter;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.oneline.fragmentbestpractic.R;

public class GalleryAdapter extends
		RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

	private List<Integer> mData;
	private LayoutInflater mInflater;
	private OnItemClickListener mOnItemClickListener;
	
	//ItemClick的回调接口 
	public interface OnItemClickListener{
		void onItemClick(View view ,int position);
	}
	
	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.mOnItemClickListener = onItemClickListener;
	}

	public GalleryAdapter(Context context, List<Integer> data) {
		this.mData = data;
		mInflater = LayoutInflater.from(context);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public ViewHolder(View itemView) {
			super(itemView);
		}

		ImageView mImg;
		TextView mText;

	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		View view = mInflater.inflate(R.layout.recycler_item, parent, false);
		ViewHolder viewHolder = new ViewHolder(view);
		viewHolder.mImg = (ImageView) view.findViewById(R.id.iv_item);
		viewHolder.mText = (TextView) view.findViewById(R.id.tv_item);
		return viewHolder;
	}

	public void onBindViewHolder(final ViewHolder holder, final int i) {
		holder.mImg.setImageResource(mData.get(i));
		holder.mText.setText("狗狗" + i);
		if(mOnItemClickListener != null) {
			holder.itemView.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					mOnItemClickListener.onItemClick(holder.itemView, i);
				}
			});
		}

	}

}
