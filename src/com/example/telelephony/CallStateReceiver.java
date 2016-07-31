package com.example.telelephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallStateReceiver extends BroadcastReceiver
{
	int callState = -1;
	TelephonyManager telM = null;
	String incomingNumber = "";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		Toast.makeText(context, "call state changed", 5).show();
		
		//check for current call state
		
		telM = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		callState = telM.getCallState();
		if(callState == TelephonyManager.CALL_STATE_RINGING)
		{
			incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			Log.e("Number", incomingNumber);
			Toast.makeText(context, "Incoming call to receive"+incomingNumber, 5).show();
			WaitThread thread = new WaitThread();
			thread.start();
		}
	}//eof onreceive
	
	class WaitThread extends Thread
	{
		public void run(){
			super.run();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//again check all state
			
			if(telM.getCallState() == TelephonyManager.CALL_STATE_RINGING)
			{
				//send sms to caller
				SmsManager manager = SmsManager.getDefault();
				manager.sendTextMessage(incomingNumber,null,"call yu later",null,null);
				
				
			}
		}
	}
	
}
