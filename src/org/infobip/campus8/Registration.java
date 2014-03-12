package org.infobip.campus8;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

import com.infobip.push.PushNotificationManager;
import com.infobip.push.RegistrationData;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends Activity {
	
	private PushNotificationManager manager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//TODO dodati provjeru jel su podaci ispunjeni
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);
		 manager = new PushNotificationManager(this);
		 final Button button = (Button) findViewById(R.id.RegisterButton);
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	//name of prefs
         		String preferencesFileName = "registerPreferences";
         		String username="";
         		String email="";
         		
         		SharedPreferences sharedpreferences=getSharedPreferences(preferencesFileName, 0);
            	
         		// from input fields to string and then to sharedPrefs
            	EditText inputUsername = (EditText) findViewById(R.id.editText1);
             	EditText inputEmail = (EditText) findViewById(R.id.editText2);
             	
             	username=inputUsername.getText().toString();
             	email=inputEmail.getText().toString();
             	Log.e("REGISTRATION","email value "+email);
             	//TODO testirati ovu IF petlju
             	if(username.equals("") || email.equals("")){
             		Toast.makeText(getBaseContext(), "Please fill all required data", Toast.LENGTH_SHORT).show();	
             	}
             	
             	else{
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user", username);
                editor.putString("email", email);
                editor.commit();
               
       		 	manager.setDebugModeEnabled(true);
       		 	manager.initialize(AppConfig.PROJECT_NUMBER, AppConfig.APPLICATION_ID, AppConfig.APPLICATION_SECRET);
                RegistrationData registrationData = new RegistrationData();
                registrationData.setUserId(username);
                List<String> channels = new ArrayList<String>();
                channels.add("test");
                registrationData.setChannels(channels);
				manager.register(registrationData);
                Toast.makeText(getBaseContext(), "Registration sucessfully completed", Toast.LENGTH_SHORT).show();
                Intent intentSubs = new Intent(Registration.this,
               	     PostRegistrationChannelSubscription.class);
               	   startActivity(intentSubs);
               	  
                finish();
             	}
           
             }

         });
    }
	//TODO KADA SE PRITISNE BACK BUTTON Treba se ugasiti
	 @Override
     public void onBackPressed() {
		
     }	

}

