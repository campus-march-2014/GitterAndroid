package org.infobip.campus8;


import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView listViewOfChannels;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		SharedPreferences regPrefs = this.getSharedPreferences("registerPreferences", 0);
		String username = regPrefs.getString("user",null);
		String email = regPrefs.getString("email",null);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

	    if ((username == null) && (email == null) )
		{
			Intent intent = new Intent(MainActivity.this,Registration.class);
			  startActivity(intent);
		}	  
			  List<String> channelList=getList();
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

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
