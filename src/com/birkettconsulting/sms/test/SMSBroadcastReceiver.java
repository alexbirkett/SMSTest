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

package com.birkettconsulting.sms.test;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSBroadcastReceiver extends BroadcastReceiver {

	private static final String ACTIVITY = "activity";
	private static final String NOTIFICATION = "notification";
	private static final int NOTIFICATION_ID = 1;
	public static final String MESSAGE_KEY = "message_key";

	@Override
	public void onReceive(Context context, Intent intent) {
		SmsMessage smsMessage = getMessageBody(intent);
		String smsBody = smsMessage.getDisplayMessageBody();
		if (smsBody != null) {
			if (smsBody.toLowerCase().contains(ACTIVITY)) {
				startActivity(context, smsBody);
				abortBroadcast();
			} else if (smsBody.toLowerCase().contains(NOTIFICATION)) {
				setNotification(smsBody, smsMessage.getDisplayOriginatingAddress(), context);
				abortBroadcast();
			}
		}
	}
	
	private SmsMessage getMessageBody(Intent intent) {
		Bundle pudsBundle = intent.getExtras();
	    Object[] pdus = (Object[]) pudsBundle.get("pdus");
	    SmsMessage message = SmsMessage.createFromPdu((byte[]) pdus[0]);
	    return message;
	}
	
	private static Intent getActivityIntent(Context context, String message) {
		Intent intent = new Intent(context, SMSTestActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.putExtra(MESSAGE_KEY, message);
        return intent;
	}
	
	private void startActivity(Context context, String message) {
        context.startActivity(getActivityIntent(context, message));
	}
	
	public static void setNotification(String message, String originatingAddress, Context context) {
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Notification notification = new Notification(R.drawable.ic_launcher,
				message, System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				getActivityIntent(context, message), PendingIntent.FLAG_UPDATE_CURRENT);

		notification.setLatestEventInfo(context, originatingAddress, message, contentIntent);

		notificationManager.cancel(NOTIFICATION_ID);
		notificationManager.notify(NOTIFICATION_ID, notification);
	}
}
