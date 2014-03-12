package org.infobip.campus8;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;

import com.infobip.push.PushNotificationManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listViewOfChannels;
	private PushNotificationManager manager;
	private String CHANNEL_LIST_PREFS_FILENAME = "ChannelList";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences regPrefs = this.getSharedPreferences("registerPreferences", 0);
		String username = regPrefs.getString("user",null);
		String email = regPrefs.getString("email",null);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		//ifobip push initialization
		 manager = new PushNotificationManager(this);
		 manager.setDebugModeEnabled(true);
		 manager.initialize(AppConfig.PROJECT_NUMBER, AppConfig.APPLICATION_ID, AppConfig.APPLICATION_SECRET);
		 //

	    if ((username == null) && (email == null) )
		{
			Intent intent = new Intent(MainActivity.this,Registration.class);
			  startActivity(intent);
		}	  
			  List<String> channelList=getList();
			  storeChannelListInSharedPrefs(channelList);
			  listViewOfChannels = (ListView) findViewById(R.id.listViewOfChannels);
			  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
					  channelList );

		         listViewOfChannels.setAdapter(arrayAdapter); 
		}

	private List<String> getList() {
		if(isConnected()){
			ChannelListCollector channelListCollector = new ChannelListCollector();
			try {
				return channelListCollector.getChannels();
				
			} catch (InterruptedException e) {
				Toast.makeText(getBaseContext(), "Exception during fetching channel list: " + e.toString(), Toast.LENGTH_SHORT).show();
			} catch (ExecutionException e) {
				Toast.makeText(getBaseContext(), "Exception during fetching channel list: " + e.toString(), Toast.LENGTH_SHORT).show();
			} catch (JSONException e) {
				Toast.makeText(getBaseContext(), "Error parsing channel list json: " + e.toString(), Toast.LENGTH_SHORT).show();
			}
			return new ArrayList<String>();
		}
		else{
			Toast.makeText(getBaseContext(), "You are not connected to the internet", Toast.LENGTH_SHORT).show();
			return new ArrayList<String>();
		}
	}

	private boolean isConnected() {
		ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	private void storeChannelListInSharedPrefs(List<String> channelList) {
		  SharedPreferences sharedpreferences = getSharedPreferences(
		    CHANNEL_LIST_PREFS_FILENAME, 0);
		  SharedPreferences.Editor editor = sharedpreferences.edit();
		  StringBuilder sb = new StringBuilder();
		  for (int i = 0; i < channelList.size(); i++) {
		   sb.append(channelList.get(i)).append(",");
		  }
		  editor.putString("Channels", sb.toString());
		  editor.commit();
		  Log.e("MainAct", "Storing in shared prefs:" + sb);
		 }
	
	@Override
	 public boolean onCreateOptionsMenu(Menu menu) {

	  menu.add(0, 1, 0, "Settings");
	  menu.add(0, 2, 0, "About");
	  return super.onCreateOptionsMenu(menu);
	 }

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {

	  switch (item.getItemId()) {
	  case 1: {
	   Log.e("MainMenu", "Selected item");
	   Intent intentSubs = new Intent(MainActivity.this,
	     PostRegistrationChannelSubscription.class);
	   startActivity(intentSubs);
	  }

	  }
	  return super.onOptionsItemSelected(item);
	 }

}
