package org.infobip.campus8;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import android.content.Context;
import android.net.ParseException;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class ChannelListCollector {
	String channels = "";
	
	public List<String> getChannels() throws InterruptedException, ExecutionException, JSONException{
		ChannelListTask task = new ChannelListTask();
		ChannelListJsonParser parser = new ChannelListJsonParser();
		channels = task.execute(new Void[0]).get();
		List<String> channelList = parser.parse(channels);
		return channelList;
	}
	
	private class ChannelListTask extends AsyncTask<Void, Void, String>{

		private final String HEADER_VALUE = "Basic cHVzaGRlbW86cHVzaGRlbW8=";
		private final String HEADER_NAME = "Authorization";
		private final String CHANNEL_LIST_URL = "https://pushapi.infobip.com/1/application/9cabf301d3db/channels";
		
		@Override
		protected String doInBackground(Void...voids) {
			HttpClient client = new DefaultHttpClient();
			HttpGet get = new HttpGet(CHANNEL_LIST_URL);
			get.setHeader(HEADER_NAME, HEADER_VALUE);
			HttpResponse responseGet = null;
			try {
				responseGet = client.execute(get);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	        HttpEntity resEntityGet = responseGet.getEntity();  
	        String output = "";
	        if (resEntityGet != null) {
	        	try {
					output = EntityUtils.toString(resEntityGet);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
			return output;
		}
		
		@Override
		protected void onPostExecute(String result){
			Log.e("do in background:", "Poèetak on post exe");
			channels += result;
		}
	}
}
