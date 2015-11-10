package com.oneline.fragmentbestpractic;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oneline.adapter.PhoneBookAdapter;
import com.oneline.bean.PhoneBook;

public class PhoneBookActivity extends Activity {

	private RecyclerView mRvPhoneBooks;
	private List<PhoneBook> mPhoneBookList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phone_book);
		initView();
	}

	private void initView() {
		mRvPhoneBooks = (RecyclerView) findViewById(R.id.rv_phone_books);
		initData();
	}

	private void initData() {
		 LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
		 mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		 mRvPhoneBooks.setLayoutManager(mLinearLayoutManager);
		 mPhoneBookList = new ArrayList<PhoneBook>();
		 getTelePhones();
		 
		 PhoneBookAdapter adapter = new PhoneBookAdapter(this, mPhoneBookList);
		 mRvPhoneBooks.setAdapter(adapter);
	}

	private void getTelePhones() {
		Cursor cursor = null;
		 try {
			  cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
				while (cursor.moveToNext()) {
					String phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
					String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					 PhoneBook phoneBook = new PhoneBook();
					 phoneBook.setTelePhoneName(phoneName);
					 phoneBook.setTelePhoneNumber(phoneNumber);
					 mPhoneBookList.add(phoneBook);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(cursor != null) {
				cursor.close();
			}
		}
		
		
	}

}
