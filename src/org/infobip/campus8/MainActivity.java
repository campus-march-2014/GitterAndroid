package org.infobip.campus8;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
	private String CHANNEL_LIST_PREFS_FILENAME = "AllChannelsFromServer";

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
	    //
	    //Initialization of message view
	    //===========================================================================
	    ListView messageListView = (ListView) findViewById(R.id.listViewOfChannels);
	    final MessageUtility utility = new MessageUtility(getApplicationContext());
	    List<Message> messageList = utility.getStoredMessages();
	    List<String> input = new ArrayList<String>();
	    for(Message msg : messageList){
	    	input.add(msg.getTitle());
	    }
	    Log.e("MAIN","MessageList: " +messageList.size());
	    Log.e("MainAct", "Input list size: " +input.size());
	    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,input);
	    messageListView.setAdapter(arrayAdapter);
	    messageListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Message message = utility.getMessageByPosition(position + 1);
				Intent viewMessage = new Intent(getApplicationContext(), ViewMessageAcitivity.class);
				viewMessage.putExtra("title",message.getTitle());
				viewMessage.putExtra("message",message.getText());
				startActivity(viewMessage);			
			}   		
		});
		//===========================================================================
	    
	    
	    final Button clearButton = (Button) findViewById(R.id.clearButton);
	    clearButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
			utility.clearAll();
			  Intent intent = getIntent();
			    finish();
			    startActivity(intent);
				
			}}
	    
	);}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	menu.add(0, 1, 0, "Settings");
	menu.add(0, 2, 0, "About");
	 return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

	 switch (item.getItemId()) {
	 case 1: {
	  Intent intentSubsOne = new Intent(MainActivity.this,PostRegistrationChannelSubscription.class);
	  startActivity(intentSubsOne);
	  break;
	 	}
	 case 2: {
		   Intent intentSubs = new Intent(MainActivity.this,AboutActivity.class);
		   startActivity(intentSubs);
		   break;
		  	}
	 }
	 return true;
	}
}
