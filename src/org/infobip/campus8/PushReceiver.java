package org.infobip.campus8;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.infobip.push.AbstractPushReceiver;
import com.infobip.push.PushNotification;

public class PushReceiver extends AbstractPushReceiver {
	private Context context;
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
		context.getApplicationContext();
		Message message = new Message(notification.getTitle(), notification.getMessage(), notification.getId());
		MessageUtility utility = new MessageUtility(context);
		if(!utility.exists(notification.getId())){
			utility.addMessage(message);
		}
		notification.vibrate();
		notification.makeSound();
		
	}
	
	@Override
    protected void onNotificationOpened(PushNotification notification, Context context) {
		Intent viewMessage = new Intent(context.getApplicationContext(), ViewMessageAcitivity.class);
		viewMessage.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		viewMessage.putExtra("title",notification.getTitle());
		viewMessage.putExtra("message",notification.getMessage());
		context.startActivity(viewMessage);
      
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
