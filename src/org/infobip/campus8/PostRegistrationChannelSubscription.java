package org.infobip.campus8;

import java.util.List;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PostRegistrationChannelSubscription extends Activity{
	
	private String CHANNEL_LIST_PREFS_FILENAME = "ChannelList";
	private ListView listViewOfChannels;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.subscription_layout);
	
	SharedPreferences sharedpreferences=this.getSharedPreferences(CHANNEL_LIST_PREFS_FILENAME, 0);
	String chList=sharedpreferences.getString("Channels",null);
	String[] ChannelList = chList.split(",");
	listViewOfChannels = (ListView) findViewById(R.id.listViewOfChannels);
	ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,ChannelList );
	listViewOfChannels.setAdapter(arrayAdapter); 
	
}


}
