package org.infobip.campus8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.datatype.Duration;

import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

public class PostRegistrationChannelSubscription extends Activity {
	private String PREFERENCES_FILENAME = "SelectedChannels";
	private String CHANNEL_LIST_PREFS_FILENAME = "AllChannelsFromServer";
	private ArrayList<String> selectedItems = new ArrayList<String>();
	private ListView listViewOfChannels;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.subscription_layout);
	List<String> channelList=getList();
	  storeChannelListInSharedPrefs(channelList);
	
	SharedPreferences sharedpreferences=this.getSharedPreferences("ChannelListSelected", 0);
	String chList=sharedpreferences.getString("Channels",null);
	String[] ChannelList = chList.split(",");
	listViewOfChannels = (ListView) findViewById(R.id.listViewOfChannels);
	ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,ChannelList );
	listViewOfChannels.setAdapter(arrayAdapter); 
	listViewOfChannels.setItemsCanFocus(false);
	listViewOfChannels.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
	LoadSelections();
	
	listViewOfChannels.setOnItemClickListener(new OnItemClickListener() {
		  public void onItemClick(AdapterView parent, View view, int position, long id) {
				    		  			 
				   }
				  });
		
	 final Button Savebutton = (Button) findViewById(R.id.saveButton);
	 Savebutton.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			saveSelectedChannelesInPrefs();
			Toast.makeText(getBaseContext(), "Saved Channel List", Toast.LENGTH_SHORT).show();
			
		}});
			
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
	
	private void saveSelectedChannelesInPrefs() {
		SharedPreferences sharedpreferences=getSharedPreferences(PREFERENCES_FILENAME, 0);
		SharedPreferences.Editor prefEditor = sharedpreferences.edit();
		String savedItems = getSavedItems();
		
		prefEditor.putString("SubscribedChannels", savedItems);
		prefEditor.commit();
				
	}
	private String getSavedItems() {
			        String savedItems = "";
		 
			        int count = this.listViewOfChannels.getAdapter().getCount();
		 
	        for (int i = 0; i < count; i++) {
		 
	            if (this.listViewOfChannels.isItemChecked(i)) {
		                if (savedItems.length() > 0) {
		                    savedItems += "," + this.listViewOfChannels.getItemAtPosition(i);
			                } else {
			                    savedItems += this.listViewOfChannels.getItemAtPosition(i);
		                }
		            }
			 
		        }
		        return savedItems;
			    }
	
	private void LoadSelections() {
		SharedPreferences settingsActivity=this.getSharedPreferences(PREFERENCES_FILENAME, 0);
	if (settingsActivity.contains("SubscribedChannels")) {  Log.e("POST","LOAD SELECTIONS3");String savedItems = settingsActivity.getString("SubscribedChannels", "");
		            this.selectedItems.addAll(Arrays.asList(savedItems.split(",")));
		            int count = this.listViewOfChannels.getAdapter().getCount();
			        for (int i = 0; i < count; i++) {
		                String currentItem = (String) this.listViewOfChannels.getAdapter().getItem(i);
		                  if (this.selectedItems.contains(currentItem)) {
			                    this.listViewOfChannels.setItemChecked(i, true);
		                }
			          }
			 
			        }
			    }
	
	
	//Po potrebi srediti
/*	 @Override
     public void onBackPressed() {
		 Intent intentSubs = new Intent(PostRegistrationChannelSubscription.this,
           	     MainActivity.class);
           	   startActivity(intentSubs);
               finish();
     }	*/

}
