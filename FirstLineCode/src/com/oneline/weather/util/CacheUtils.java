package com.oneline.weather.util;

import com.oneline.fragmentbestpractic.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CacheUtils {
	
	private static CacheUtils mInstance;
	private Context mCtx;
	private CacheUtils(Context ctx){
		mCtx = ctx;
	}
	
	public static CacheUtils getInstance(Context ctx){
		if(mInstance == null) {
			synchronized (CacheUtils.class) {
				if(mInstance == null) {
					mInstance = new CacheUtils(ctx);
				}
			}
		}
		return mInstance;
	}
	public int getMaxMemory(){
		return (int) (Runtime.getRuntime().maxMemory() / 1024);  
	}
	//根据原始图片大小和你能提供的最大图片计算比率
	public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight){
		int imageWidth = options.outWidth;
		int imageHeight = options.outHeight;
		int inSampleSize = 1;
		if(imageWidth > reqWidth || imageHeight > reqHeight) {
			// 计算出实际宽高和目标宽高的比率  
			final int widthRatio = Math.round(imageWidth/reqWidth);
			final int heightRatio = Math.round(imageHeight/reqHeight);
			 // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高  
	        // 一定都会大于等于目标的宽和高。
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;  
		}
		return inSampleSize;
		
	}
	//在展示高分辨率图片的时候，最好先将图片进行压缩。
	public Bitmap decodeSampledBitmapFromResource(Resources res, int resId,  
	        int reqWidth, int reqHeight){
		// 第一次解析将inJustDecodeBounds设置为true，来获取图片大小  
		BitmapFactory.Options options = new BitmapFactory.Options();
		//true就可以让解析方法禁止为bitmap分配内存
		options.inJustDecodeBounds  = true;
		BitmapFactory.decodeResource(res, resId, options);
		// 调用上面定义的方法计算inSampleSize值  
		calculateInSampleSize(options, reqWidth, reqHeight);
		// 使用获取到的inSampleSize值再次解析图片  
	    options.inJustDecodeBounds = false; 
	   int newHeight =  options.outHeight;
	   int newWidth = options.outWidth;
	    return BitmapFactory.decodeResource(res, resId, options);  
	}
}
