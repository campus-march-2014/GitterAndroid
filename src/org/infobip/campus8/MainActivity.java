package org.infobip.campus8;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.os.Build;

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
			  
			  listViewOfChannels = (ListView) findViewById(R.id.listViewOfChannels);
			  //IZ Arrayliste u listu na screenu
			  ArrayList<String> your_array_list = new ArrayList<String>();
			  your_array_list.add("foo");
			  your_array_list.add("Channel2bar");
			  your_array_list.add("Channel2");
			  your_array_list.add("Channel23");
			  your_array_list.add("foo");
			  your_array_list.add("bar");
		         your_array_list.add("Channel2");
		         your_array_list.add("Channel232q3");
		         
		         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
		                 your_array_list );

		         listViewOfChannels.setAdapter(arrayAdapter); 
		
			
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
