package org.infobip.campus8;

import android.content.Context;
import android.widget.Toast;

import com.infobip.push.AbstractPushReceiver;
import com.infobip.push.PushNotification;

public class PushReceiver extends AbstractPushReceiver {

	@Override
	public void onRegistered(Context context) {
		Toast.makeText(context, "Successfully registered.", Toast.LENGTH_SHORT).show();
		
	}
	
	@Override
    protected void onRegistrationRefreshed(Context context) {
        Toast.makeText(context, "Registration is refreshed.", Toast.LENGTH_SHORT).show();
    }

	@Override
	public void onNotificationReceived(PushNotification notification, Context context) {
		Toast.makeText(context, "Received notification: " + notification.toString(), Toast.LENGTH_SHORT).show();
		
	}
	
	@Override
    protected void onNotificationOpened(PushNotification notification, Context context) {
        Toast.makeText(context, "Notification opened.", Toast.LENGTH_LONG).show();
    }

	@Override
	public void onUnregistered(Context context) {
		Toast.makeText(context, "Successfully unregistered.", Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onError(int reason, Context context) {
		Toast.makeText(context, "Error occurred: " + reason, Toast.LENGTH_SHORT).show();
		
	}

}
