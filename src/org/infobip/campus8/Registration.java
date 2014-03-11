package org.infobip.campus8;

import javax.xml.datatype.Duration;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//TODO dodati provjeru jel su podaci ispunjeni
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);
	
		 final Button button = (Button) findViewById(R.id.RegisterButton);
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	//name of prefs
         		String registerPreferences = "registerPreferences";
         		String username;
         		String email;
         		SharedPreferences sharedpreferences=getSharedPreferences(registerPreferences, 0);
            	 // from input fields to string and then to sharedPrefs
            	EditText inputUsername = (EditText) findViewById(R.id.editText1);
             	EditText inputEmail = (EditText) findViewById(R.id.editText2);
             	username=inputUsername.getText().toString();
             	email=inputEmail.getText().toString();
             	Log.e("REGISTER", "1");
             	Log.e("REGISTER", "Username vrijednost: " +username);
             	Log.e("REGISTER", "email: " +email);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user", username);
                editor.putString("email", email);
                editor.commit();
                Log.e("REGISTER", "GOTOV");
                Log.e("REGISTER", ""+inputEmail);
                Toast.makeText(getBaseContext(), "Registration sucessfully completed", Toast.LENGTH_SHORT).show();
                finish();
           
             }

         });

	
	
	
}}

