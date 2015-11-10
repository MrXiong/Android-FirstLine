
  
package com.oneline.broadcast;  

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver{

	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "received in MyBroadcastReceiver",
				Toast.LENGTH_SHORT).show();
	}

}
  