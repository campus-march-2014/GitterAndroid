package org.infobip.campus8;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registeration extends Activity {
	//name of prefs
	String registerPreferences;
	String username;
	String email;
	SharedPreferences sharedpreferences=getSharedPreferences(registerPreferences, MODE_PRIVATE);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration_screen);
	
		 final Button button = (Button) findViewById(R.id.RegisterButton);
         button.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {
            	 
            	 // from input fields to string and then to sharedPrefs
            	EditText inputUsername = (EditText) findViewById(R.id.editText1);
             	EditText inputEmail = (EditText) findViewById(R.id.textView2);
             	username=inputUsername.getText().toString();
             	email=inputEmail.getText().toString();
             	
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user", username);
                editor.putString("email", email);
                editor.commit();
                

           /*     if (sharedpreferences.contains(username))
                {
                   username.setText(sharedpreferences.getString(username, ""));

                }
                if (sharedpreferences.contains(mail))
                {
                   mail.setText(sharedpreferences.getString(username, ""));

                }*/
             }

         });

	
	
	
}}

