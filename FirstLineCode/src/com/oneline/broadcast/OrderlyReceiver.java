
  
package com.oneline.broadcast;  

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OrderlyReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "received in OrderlyBroadCast",
				Toast.LENGTH_SHORT).show();
		abortBroadcast();//截断广播
		 
	}

}
  