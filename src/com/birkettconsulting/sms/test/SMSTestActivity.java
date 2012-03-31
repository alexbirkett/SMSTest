package com.birkettconsulting.sms.test;

import android.app.Activity;
/**
 * 
 *  Copyright 2012 Birkett Consulting
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * 
 */

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SMSTestActivity extends Activity {
 
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    	setMessageIfNotNull(getMessageFromIntent(getIntent()));
    }
	
    @Override
    protected void onNewIntent(Intent intent) {
    	setMessageIfNotNull(getMessageFromIntent(intent));
    }
    
    private void setMessageIfNotNull(String text) {
    	if (text != null) {
	    	TextView textView = (TextView) findViewById(R.id.message_text);
	    	textView.setText(text);
    	}
    }
    
    private String getMessageFromIntent(Intent intent) {
    	return intent.getStringExtra(SMSBroadcastReceiver.MESSAGE_KEY);
    }
}