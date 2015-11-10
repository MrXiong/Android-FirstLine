
  
package com.oneline.base;  

import com.oneline.utils.ActivityController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends Activity{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		ActivityController.addActivity(this);
	}

	public void sendActivity(Context context, Class<?> cls) {
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
	public void sendActivityFlags(Context context, Class<?> cls) {
		Intent intent = new Intent(context, cls);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		context.startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy(); 
		ActivityController.removeActivity(this);
	}
}
  