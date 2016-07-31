package com.example.telelephony;


public class MainActivity extends Activity {
	TextView textViewData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewData = (TextView) findViewById(R.id.textView1);
        TelephonyManager telM = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        int dataState = telM.getDataState();
        int callState = telM.getCallState();
        
        textViewData.setText("DS:"+dataState +"CS:"+ callState);
        
         if(dataState == TelephonyManager.DATA_CONNECTED)
         {
        	 //download the data
        	 
         }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
