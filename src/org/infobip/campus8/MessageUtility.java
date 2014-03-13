package org.infobip.campus8;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

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
			String id = sharedpreferences.getString(AppConfig.STORED_MESSAGE_ID+i, "");
			Message message = new Message(title, text, id);
			messageList.add(message);
		}
		Log.e("getStoredMEssages", "from method: " + messageList.size());
		return messageList;
	}

	public void addMessage(Message message) {
		SharedPreferences sharedpreferences = context.getSharedPreferences(AppConfig.STORED_MESSAGES_PREFERENCES, 0);
		int messageCount = sharedpreferences.getInt(AppConfig.STORED_MESSAGES_NUMBER, 0);
		int newMessageCount = messageCount + 1;
		Log.e("from addMessage", "message count: " + messageCount);
		Log.e("from addMessage", "new message count: " + newMessageCount);
		SharedPreferences.Editor editor = sharedpreferences.edit();
		editor.putInt(AppConfig.STORED_MESSAGES_NUMBER, newMessageCount);
		editor.putString(AppConfig.STORED_MESSAGE_TITLE_KEY+newMessageCount, message.getTitle());
		editor.putString(AppConfig.STORED_MESSAGE_TEXT_KEY+newMessageCount, message.getText());
		editor.putString(AppConfig.STORED_MESSAGE_ID+newMessageCount, message.getId());
		editor.commit();
	}
	
	public boolean exists(String inputId){
		context.getApplicationContext();
		SharedPreferences sharedpreferences = context.getSharedPreferences(AppConfig.STORED_MESSAGES_PREFERENCES, 0);
		int messageCount = sharedpreferences.getInt(AppConfig.STORED_MESSAGES_NUMBER, 0);
		for(int i = 1; i<= messageCount; i++){
			String id = sharedpreferences.getString(AppConfig.STORED_MESSAGE_ID+i, "");
			if(id.equals(inputId)){
				return true;
			}
		}
		return false;
	}
	
	public Message getMessageByPosition(int position){
		context.getApplicationContext();
		SharedPreferences sharedpreferences = context.getSharedPreferences(AppConfig.STORED_MESSAGES_PREFERENCES, 0);
		String title = sharedpreferences.getString(AppConfig.STORED_MESSAGE_TITLE_KEY+position, "");
		String text = sharedpreferences.getString(AppConfig.STORED_MESSAGE_TEXT_KEY+position, "");
		String id = sharedpreferences.getString(AppConfig.STORED_MESSAGE_ID+position, "");
		Message message = new Message(title, text, id);
		return message;
	}

	public void clearAll() {
		context.getApplicationContext();
		SharedPreferences sharedpreferences = context.getSharedPreferences(AppConfig.STORED_MESSAGES_PREFERENCES, 0);
		sharedpreferences.edit().clear().commit();
	}
	
	
}
