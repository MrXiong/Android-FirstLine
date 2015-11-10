
  
package com.oneline.provider;  

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

public class OneLineProvider extends ContentProvider{
	public static final int TABLE1_DIR = 0;
	public static final int TABLE1_ITEM = 1;
	public static final int TABLE2_DIR = 2;
	public static final int TABLE2_ITEM = 3;
	private static final String TAG = OneLineProvider.class.getSimpleName();
	private static UriMatcher uriMatcher;
	static {
	uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	uriMatcher.addURI("com.oneline.fragmentbestpractic.provider", "table1", TABLE1_DIR);
	uriMatcher.addURI("com.oneline.fragmentbestpractic.provider ", "table1/#", TABLE1_ITEM);
	uriMatcher.addURI("com.oneline.fragmentbestpractic.provider ", "table2", TABLE2_ITEM);
	uriMatcher.addURI("com.oneline.fragmentbestpractic.provider ", "table2/#", TABLE2_ITEM);
	}
	public boolean onCreate() {
		return false; 
	}

	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		switch (uriMatcher.match(uri)) {
		case TABLE1_DIR:
		//  查询table1 表中的所有数据
			Toast.makeText(getContext(), "查询TABLE1_DIR 表中的所有数据", 0).show();
		break;
		case TABLE1_ITEM:
		//  查询table1 表中的单条数据
			Toast.makeText(getContext(), "查询TABLE1_ITEM 表中的所有数据", 0).show();
		break;
		case TABLE2_DIR:
		//  查询table2 表中的所有数据
			Toast.makeText(getContext(), "查询TABLE2_DIR 表中的所有数据", 0).show();
		break;
		case TABLE2_ITEM:
		//  查询table2 表中的单条数据
			Toast.makeText(getContext(), "查询TABLE2_ITEM 表中的所有数据", 0).show();
		break;
		default:
		break;
		}
		return null; 
	}

	public String getType(Uri uri) {
		switch (uriMatcher.match(uri)) {
		case TABLE1_DIR:
		return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
		case TABLE1_ITEM:
		return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
		case TABLE2_DIR:
		return "vnd.android.cursor.dir/vnd.com.example.app.provider.table2";
		case TABLE2_ITEM:
		return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
		default:
		break;
		}
		return null; 
	}

	public Uri insert(Uri uri, ContentValues values) {
		switch (uriMatcher.match(uri)) {
		case TABLE1_DIR:
		//  查询table1 表中的所有数据
			Log.v(TAG, "TABLE1_DIR");
			Toast.makeText(getContext(), "查询TABLE1_DIR 表中的所有数据", 0).show();
		break;
		case TABLE1_ITEM:
		//  查询table1 表中的单条数据
			Log.v(TAG, "TABLE1_ITEM");
			Toast.makeText(getContext(), "查询TABLE1_ITEM 表中的所有数据", 0).show();
		break;
		case TABLE2_DIR:
		//  查询table2 表中的所有数据
			Log.v(TAG, "TABLE2_DIR");
			Toast.makeText(getContext(), "查询TABLE2_DIR 表中的所有数据", 0).show();
		break;
		case TABLE2_ITEM:
		//  查询table2 表中的单条数据
			Log.v(TAG, "TABLE2_ITEM");
			Toast.makeText(getContext(), "查询TABLE2_ITEM 表中的所有数据", 0).show();
		break;
		default:
		break;
		}
		return null; 
	}

	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0; 
	}

	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0; 
	}

}
  