package com.oneline.fragmentbestpractic;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ChoosePicActivity extends Activity implements OnClickListener {

	public static final int PHOTO_REQUEST_GALLERY = 1;//相机
	public static final int PHOTO_REQUEST_CAREMA = 2;
	public static final int PHOTO_REQUEST_CUT = 3;
	public static final String IMAGE_NAME = System.currentTimeMillis() + ".jpg";
	
	private Button mBtnTakePhoto;
	private Button mBtnFromAlbum;
	private ImageView mIvPicture;
	private File tempFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_pic);
		initView();
	}

	private void initView() {
		mBtnTakePhoto = (Button) findViewById(R.id.btn_take_photo);
		mBtnFromAlbum = (Button) findViewById(R.id.btn_from_album);
		mIvPicture = (ImageView) findViewById(R.id.iv_picture);
		mBtnTakePhoto.setOnClickListener(this);
		mBtnFromAlbum.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_take_photo:
			camera();
			break;
		case R.id.btn_from_album:
			gallery();
			break;

		default:
			break;
		}

	}
	/*
     * 从相册获取
     */
    public void gallery() {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }
 
    /*
     * 从相机获取
     */
    public void camera() {
        // 激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
        	tempFile = new File(Environment.getExternalStorageDirectory(),
            		IMAGE_NAME+".jpg");
            // 从文件中创建uri
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
    }

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                crop(uri);
            }
 
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            // 从相机返回的数据
            if (hasSdcard()) {
            	crop(Uri.fromFile(tempFile));
            } else {
                Toast.makeText(ChoosePicActivity.this, "未找到存储卡，无法存储照片！", 0).show();
            }
 
        } else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");
                this.mIvPicture.setImageBitmap(bitmap);
            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
 
        }
 
        super.onActivityResult(requestCode, resultCode, data);
    }
	/*
     * 剪切图片
     */
    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);
 
        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    public static boolean hasSdcard() {
    	return Environment.MEDIA_MOUNTED.equals(Environment
    	.getExternalStorageState());
    	}
}
