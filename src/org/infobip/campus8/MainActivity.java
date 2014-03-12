package org.infobip.campus8;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		getList();
	}

	private void getList() {
		if(isConnected()){
			ChannelListCollector channelListCollector = new ChannelListCollector();
			String channels;
			try {
				channels = (channelListCollector.getChannels()).toString();
				Toast.makeText(getBaseContext(), channels, Toast.LENGTH_LONG).show();
			} catch (InterruptedException e) {
				Toast.makeText(getBaseContext(), "Exception during fetching channel list: " + e.toString(), Toast.LENGTH_SHORT).show();
			} catch (ExecutionException e) {
				Toast.makeText(getBaseContext(), "Exception during fetching channel list: " + e.toString(), Toast.LENGTH_SHORT).show();
			} catch (JSONException e) {
				Toast.makeText(getBaseContext(), "Error parsing channel list json: " + e.toString(), Toast.LENGTH_SHORT).show();
			}
		}
		else{
			 Toast.makeText(getBaseContext(), "You are not connected to the internet", Toast.LENGTH_SHORT).show();
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
