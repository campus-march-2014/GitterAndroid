package org.infobip.campus8;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;

public class MessageUtility {
	private static Context context;
	
	public MessageUtility(Context context){
		this.context = context;
	}
	
	public List<Message> getStoredMessages() {
		context.getApplicationContext();
		SharedPreferences sharedpreferences = context.getSharedPreferences(AppConfig.STORED_MESSAGES_PREFERENCES, 0);
		int messageCount = sharedpreferences.getInt(AppConfig.STORED_MESSAGES_NUMBER, 0);
		List<Message> messageList = new ArrayList<Message>();
		for(int i = 1; i<= messageCount; i++){
			String title = sharedpreferences.getString(AppConfig.STORED_MESSAGE_TITLE_KEY+i, "");
			String text = sharedpreferences.getString(AppConfig.STORED_MESSAGE_TEXT_KEY+i, "");
			Message message = new Message(title, text);
			messageList.add(message);
		}
		return messageList;
	}

	public void addMessage(Message message) {
		SharedPreferences sharedpreferences = context.getSharedPreferences(AppConfig.STORED_MESSAGES_PREFERENCES, 0);
		int messageCount = sharedpreferences.getInt(AppConfig.STORED_MESSAGES_NUMBER, 0);
		int newMessageCount = messageCount + 1;
		
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putInt(AppConfig.STORED_MESSAGES_NUMBER, newMessageCount);
		editor.putString(AppConfig.STORED_MESSAGE_TITLE_KEY+newMessageCount, message.getTitle());
		editor.putString(AppConfig.STORED_MESSAGE_TEXT_KEY+newMessageCount, message.getTitle());
		editor.commit();
	}
	
	
}
